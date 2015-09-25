(ns unibull.datomic-test
  (:require [midje.sweet :refer :all]
            [unibull.datomic :as db]
            [unibull.test-db :refer [create-empty-in-mem-db]]
            ))

(background
  (around :facts (let [c (create-empty-in-mem-db)]
                   ?form)))

(fact "we can create and get a class!"
      (do
        (db/create-class c "cs-112")
        (db/get-classes c))
      => #{["cs-112"]})

(fact "we can create multiple classes"
      (do
        (db/create-class c "cs-112")
        (db/create-class c "cs-109")
        (db/create-class c "cs-104a")
        (db/get-classes c))
      => #{["cs-112"] ["cs-109"] ["cs-104a"]})

(fact "we can add threads to classes"
      (do
        (db/create-class c "cs-112")
        (db/add-thread c "haskell-101" "cs-112")
        (db/find-threads-for-class c "cs-112"))
      => #{["haskell-101"]})

(fact "we can add threads & not have them mesh"
      (do
        (db/create-class c "cs-109")
        (db/create-class c "cs-112")
        (db/add-thread c "haskell-101" "cs-112")
        (db/add-thread c "112-stuff" "cs-112")
        (db/add-thread c "109-stuff" "cs-109")
        (db/find-threads-for-class c "cs-112"))
      => #{["haskell-101"] ["112-stuff"]})
