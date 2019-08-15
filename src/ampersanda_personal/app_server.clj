(ns ampersanda-personal.app-server
  (:require
    [ring.util.response :refer [resource-response content-type not-found]]))

(def route-set #{"/" "/contact" "/blog" "/home"})

(defn handler [req]
  (or
   (when (route-set (:uri req))
     (some-> (resource-response "index.html" {:root "public"})
             (content-type "text/html; charset=utf-8")))
   (not-found "Not found")))
