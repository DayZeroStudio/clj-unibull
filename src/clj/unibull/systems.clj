(ns unibull.systems
  (:require
    [com.stuartsierra.component :as component]
    (system.components
      [jetty :refer [new-web-server]]
      )
    [unibull.app :refer [app]]
    ))

(defn dev-system [cfg]
  (component/system-map
    :web (new-web-server (:http-port (:web cfg)) app)))

(defn prod-system [cfg]
  (component/system-map
    :web (new-web-server (:http-port (:web cfg)) app)))
