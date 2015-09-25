(ns unibull.app
  (:require [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [compojure.core :refer [defroutes context GET POST DELETE]]
            [unibull.api :as api]
            [reloaded.repl :refer [system]]))

(defroutes routes
  (GET "/" [] "index.html")

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
      (wrap-defaults api-defaults)
      (wrap-edn-params)
      (wrap-cors :access-control-allow-origin [#"http://localhost:3449.*" #"http://localhost:3000.*"]
                 :access-control-allow-methods [:get :post :delete])))
