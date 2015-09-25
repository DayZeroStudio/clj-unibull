(ns unibull.api
  (:require [unibull.datomic :as db]))

(defmacro def-api
  [name params & body]
  `(defn ~name ~params
     (try
       (str (do ~@body))
       (catch Exception ~'e
         (println ~'e)
         (str {:err ~'e})))))

(def-api get-classes [{:keys [datomic]}]
  (db/get-classes datomic))

(def-api add-class [{:keys [datomic]} class-name]
  (db/create-class datomic class-name)
  (db/get-classes datomic))

(def-api delete-class [{:keys [datomic]} class-name]
  (db/delete-class datomic class-name)
  (db/get-classes datomic))
