(ns unibull.model
  (:require [cljs.core.async :refer [chan]]
            [unibull.ajax :refer [GET POST]]
            ))

(defn- add-todo [state text]
  (POST "/class" {:name text})
  (update-in state [:todos] conj {:text text}))

(defonce state (atom {:todos []}))
(defn init []
  {:state state
   :channels {:add-todo (chan)}
   :consumers {:add-todo add-todo}})
