(ns unibull.systems
  (:require
    [com.stuartsierra.component :as component]
    ;[system.core :refer [defsystem]] ;add environ & remove cfg (?)
    (system.components
      [jetty :refer [new-web-server]]
      [datomic :refer [new-datomic-db]]
      )
    [unibull.app :refer [app]]
    ))

(defn dev-system [cfg]
  (component/system-map
    :web (new-web-server (:port (:web cfg)) app)
    :datomic (new-datomic-db (:url (:datomic cfg)))))

(defn prod-system [cfg]
  (component/system-map
    :web (new-web-server (:port (:web cfg)) app)
    :datomic (new-datomic-db (:url (:datomic cfg)))))
