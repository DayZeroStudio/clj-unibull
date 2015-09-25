(ns unibull.test-db
  (:require [datomic.api :as d]))

(defn create-empty-in-mem-db []
  (let [uri "datomic:mem://unibull-test-db"]
    (d/delete-database uri)
    (d/create-database uri)
    (let [conn (d/connect uri)
          schema (load-file "resources/datomic/schema.edn")]
      (d/transact conn schema)
      {:conn conn})))
