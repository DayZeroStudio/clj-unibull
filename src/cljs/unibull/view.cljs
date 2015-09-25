(ns unibull.view
  (:require
    [quiescent.core :as q]
    [quiescent.dom :as d]
    [clojure.string :as s]
    [cljs.core.async :refer [>!]])
  (:require-macros
    [cljs.core.async.macros :refer [go]]))

(defn event->type [e]
  ({13 :enter} (.-keyCode e)))

(q/defcomponent TodoItem
  [{:keys [text]}]
  (d/li {}
        (d/div {}
               (d/label {} text))))

(q/defcomponent TodoList
  [{:keys [todos]} channels]
  (apply d/ul {:id "todo-list"}
         (map TodoItem todos)))

(q/defcomponent App
  [state {:keys [add-todo] :as chs}]
  (d/input {:id "new-todo"
            :placeholder "fill me up"
            :onKeyDown (fn [e]
                         (when (= :enter (event->type e))
                           (let [target (.-target e)
                                 v (.-value target)]
                             (when (not (empty? (s/trim v)))
                               (go (>! add-todo v))
                               (set! (.-value target) "")))))}
           (TodoList state chs)))
