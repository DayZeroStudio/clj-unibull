(ns user
  (:require [reloaded.repl :refer [system init start stop go reset]]
            [unibull.systems :refer [dev-system]]))

(def config
  {:web {:http-port 3000}})

(reloaded.repl/set-init! #(dev-system config))
