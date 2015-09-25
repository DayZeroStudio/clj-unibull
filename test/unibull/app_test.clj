(ns unibull.app-test
  (:require [midje.sweet :refer :all]
            [unibull.app :refer [routes]]
            [unibull.datomic :as db]
            [unibull.test-db :refer [create-empty-in-mem-db]]
            ))

(defn- request [resource & [params method]]
   (routes {:request-method (or method :get)

            :uri resource
            :params params}))

(defn- get-classes []
  (-> (request "/class")
      :body read-string))

(defn- post-class [class-name]
  (-> (request "/class" {:name class-name} :post)
      :body read-string))

(defn- delete-class [class-name]
  (-> (request "/class" {:name class-name} :delete)
      :body read-string))

(background
  (around :facts (with-redefs [reloaded.repl/system
                               {:datomic (create-empty-in-mem-db)}]
                   ?form)))

(fact "not-found => 404"
      (-> (request "/wrong-stuff")
          :status)
      => 404)

(fact "start with no classes"
      (get-classes)
      => [])

(fact "can add classes"
      (post-class "name")
      => [["name"]]
      (do (post-class "foo")
          (get-classes))
      => [["name"] ["foo"]]
      )

(fact "we can delete a class"
      (do (post-class "foo")
          (delete-class "foo"))
      => [])

(fact "we can't add two classes with the same name"
      (do (post-class "bar")
          (post-class "bar"))
      => [["bar"]])
