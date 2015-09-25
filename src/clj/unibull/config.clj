(ns unibull.config
  )

(def ^:private dflt-cfg
  {:web {:port 3000}
   :datomic {:url "datomic:free://localhost:4334/unibull"}})

(def env
  ;; DEVELOPMENT
  {:dev  (merge dflt-cfg
                {})

   ;; TESTING
   :test (merge dflt-cfg
                {:web {:port 3001}
                 :datomic {:url "datomic:mem://unibull-test-db"}})

   ;; PRODUCTION
   :prod (merge dflt-cfg
                {})})
