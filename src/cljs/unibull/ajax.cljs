(ns unibull.ajax
  (:require [ajax.core :as ajax]))

(def base-url
  "http://localhost:3000")

(def def-opts {:handler println
               :error-handler println
               :format :edn
               :response-format :edn})

(defn GET [url & [opts]]
  (ajax/GET (str base-url url)
            (merge def-opts
                   opts)))

(defn POST [url data & [opts]]
  (ajax/POST (str base-url url)
             (merge def-opts
                    opts {:params data})))
