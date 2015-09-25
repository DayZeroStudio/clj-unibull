(ns unibull.api-test
  (:require [midje.sweet :refer :all]
            [unibull.api :as api]))

(fact "def-api works"
      (api/def-api add-class [{:keys [datomic]} class-name]
        (db/create-class datomic class-name)
        (db/get-classes datomic))
      =expands-to=>
      (clojure.core/defn add-class [{:keys [datomic]} class-name]
        (try
          (clojure.core/str
            (do (db/create-class datomic class-name)
                (db/get-classes datomic)))
          (catch java.lang.Exception e
            (clojure.core/println e)
            (clojure.core/str {:err e})))))
