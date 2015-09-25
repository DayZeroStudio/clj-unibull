(ns unibull.datomic
  (:require [datomic.api :as d]))

(defn- debug [x]
  (do (println (str x))
      x))

(defn find-class-id [{conn :conn} class-name]
  (-> (d/q '[:find ?eid ?class-name
             :in $ ?class-name
             :where [?eid :class/name ?class-name]]
           (d/db conn)
           class-name)
      ffirst))

(defn create-class [{conn :conn} class-name]
  @(d/transact conn
    [{:db/id (d/tempid :db.part/user)
      :class/name class-name}]))

(defn get-classes [{conn :conn}]
  (d/q '[:find ?class-name
         :where [_ :class/name ?class-name]]
       (d/db conn)))

(defn delete-class [{conn :conn :as c} class-name]
  (let [class-id (find-class-id c class-name)]
    @(d/transact conn
      [[:db.fn/retractEntity class-id]])))

(defn add-thread [{conn :conn :as c} thread-name class-name]
  (let [thread-id (d/tempid :db.part/user)]
    @(d/transact conn
      [{:db/id thread-id
        :thread/name thread-name}
       {:db/id (find-class-id c class-name)
        :class/threads thread-id}])))

(defn find-threads-for-class [{conn :conn} class-name]
  (d/q '[:find ?thread-name
         :in $ ?class-name
         :where [?eid :class/name ?class-name]
                [?eid :class/threads ?threads]
                [?threads :thread/name ?thread-name]]
       (d/db conn)
       class-name))
