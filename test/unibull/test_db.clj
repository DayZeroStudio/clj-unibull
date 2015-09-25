(ns unibull.test-db
  (:require
    [datomic.api :as d]
    [unibull.config :refer [env]]))

(def cfg (:test env))
(defn create-empty-in-mem-db []
  (let [uri (:url (cfg :datomic))]
    (d/delete-database uri)
    (d/create-database uri)
    (let [conn (d/connect uri)
          schema (load-file "resources/datomic/schema.edn")]
      (d/transact conn schema)
      {:conn conn})))
