(ns unibull.core
  (:gen-class)
  (:require
    [reloaded.repl :refer [system init start stop go reset]]
    [unibull.systems :refer [prod-system]]
    ))

(defn -main []
  (reloaded.repl/set-init! prod-system)
  (go))
