(ns unibull.systems
  (:require
    ;[com.stuartsierra.component :as component]
    [system.core :refer [defsystem]]
    (system.components
      [jetty :refer [new-web-server]]
      [datomic :refer [new-datomic-db]]
      )
    [unibull.app :refer [app]]
    [unibull.config :refer [env]]
    ))

(let [cfg (:dev env)]
  (defsystem dev-system
    [:web (new-web-server (:port (:web cfg)) app)
     :datomic (new-datomic-db (:url (:datomic cfg)))]))

(let [cfg (:prod env)]
  (defsystem prod-system
    [:web (new-web-server (:port (:web cfg)) app)
     :datomic (new-datomic-db (:url (:datomic cfg)))]))
