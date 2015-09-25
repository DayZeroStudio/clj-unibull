(ns ^:figwheel-always unibull.core
  (:require
    [quiescent.core :as q]
    [quiescent.dom :as d]
    [unibull.model :as model]
    [unibull.view :as view])
  (:require-macros
    [cljs.core.async.macros :refer [go]]))

(enable-console-print!)

(defn render [{:keys [state channels]}]
  (println ::render)
  (q/render (view/App @state channels)
            (.getElementById js/document "main")))

(defn init-updates
  [{:keys [consumers channels state] :as app}]
  (doseq [[ch update-fn] consumers]
    (go
      (while true
        (let [val (<! (get channels ch))
              _ (println {:action ch :val val})
              new-state (swap! state update-fn val)]))))
  app)

(def app (model/init))
(defn on-js-reload []
  (println (apply str (repeat 64 "-")))
  (swap! (:state app) update-in [:__figwheel_counter] inc))
(add-watch (:state app) ::render #(render app))
(def *main*
  (-> app
      init-updates
      render))
