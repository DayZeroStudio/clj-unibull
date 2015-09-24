(ns unibull.app
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.core :refer [defroutes GET POST]]))

(defroutes routes
  (GET "/" [] "WELCOME")
  (POST "/" [] "WHAT!?!")

  (route/resources "/")
  (route/not-found "ERROR 404, NOT FOUND")
  )

(def app
  (-> routes
      (handler/site)))
