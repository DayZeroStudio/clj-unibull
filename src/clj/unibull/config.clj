(ns unibull.config
  )

(def ^:private dflt-cfg
  {:web {:port 3000}
   :datomic {:url "datomic:free://localhost:4334/unibull"}})

(def env
  {:dev  (merge dflt-cfg
                {})
   :test (merge dflt-cfg
                {:datomic {:url "datomic:mem://unibull-test-db"}})
   :prod (merge dflt-cfg
                {})})
