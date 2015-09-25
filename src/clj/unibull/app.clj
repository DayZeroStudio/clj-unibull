(ns unibull.app
  (:require
    [compojure.handler :as handler]
    [compojure.route :as route]
    [ring.middleware.edn :refer [wrap-edn-params]]
    [ring.util.response :refer [resource-response]]
    [compojure.core :refer [defroutes context GET POST DELETE]]
    [unibull.api :as api]
    [reloaded.repl :refer [system]]))

(defroutes routes
  (GET "/" []
       (resource-response "index.html"
                          {:root "public"}))

  (context "/class" []
           (GET "/" []
                (api/get-classes system))
           (POST "/" [name]
                 (api/add-class system name))
           (DELETE "/" [name]
                   (api/delete-class system name)))

  (route/resources "/")
  (route/not-found "ERROR 404, NOT FOUND")
  )

(def app
  (-> routes
      (handler/site)
      (wrap-edn-params)
      ))
