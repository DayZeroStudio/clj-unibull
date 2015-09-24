(ns unibull.core
  (:gen-class)
  (:require
    [reloaded.repl :refer [system init start stop go reset]]
    [unibull.systems :refer [prod-system]]))

(def config
  {:web {:http-port 3000}})

(defn -main []
  (reloaded.repl/set-init! #(prod-system config))
  (go))
