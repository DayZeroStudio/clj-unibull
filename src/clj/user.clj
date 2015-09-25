(ns user
  (:require [reloaded.repl :refer [system init start stop go reset]]
            [unibull.systems :refer [dev-system]]))

(def config
  {:web {:port 3000}
   :datomic {:url "datomic:free://localhost:4334/unibull"}})

(reloaded.repl/set-init! #(dev-system config))
