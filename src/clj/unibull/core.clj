(ns unibull.core
  (:gen-class)
  (:require
    [reloaded.repl :refer [system init start stop go reset]]
    [unibull.systems :refer [prod-system]]))

(def config
  {:web {:port 3000}
   :datomic {:url "datomic:free://localhost:4334/unibull"}})

(defn -main []
  (reloaded.repl/set-init! #(prod-system config))
  (go))
