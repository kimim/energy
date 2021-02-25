(ns vorstellung.routes.home
  (:require
   [clojure.java.io :as io]
   [ring.util.response :refer [redirect]]
   [ring.util.http-response :as response]
   [buddy.auth :refer [authenticated?]]
   [vorstellung.db.core :as db]
   [vorstellung.layout :as layout]
   [vorstellung.middleware :as middleware]
   [vorstellung.upload :as upload]))

(defn home-page [request app]
  (layout/render request "home.html" {:script app}))

(defn home-routes []
  [""
   {:middleware [#_middleware/wrap-csrf
                 #_auth/wrap-auth
                 middleware/wrap-formats]}
   ["/"             {:get #(home-page % "/js/home.js")}]])
