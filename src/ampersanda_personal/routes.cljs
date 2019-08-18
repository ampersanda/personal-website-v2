(ns ^:figwheel-hooks ampersanda-personal.routes
  (:require
    [bidi.bidi :as bidi]
    [ampersanda-personal.states :as states]
    [pushy.core :as pushy]))

(def routes ["/"
             {""         :home
              "blog"     :blog
              "projects" :projects
              "about"    :about
              "contact"  :contact}])

(def routes-meta
  {:home     "Mochamad Lucky Pradana"
   :blog     "Stories - Mochamad Lucky Pradana"
   :projects "Projects - Mochamad Lucky Pradana"
   :about    "About Me - Mochamad Lucky Pradana"
   :contact  "Contact - Mochamad Lucky Pradana"})

(defn- dispatch-route [matched-route]
  (let [panel-name (keyword (str (name (:handler matched-route)) "-panel"))]
    (set! js/document.title ((:handler matched-route) routes-meta))
    (swap! states/app-state assoc :route panel-name)))

(defn- parse-url [url]
  (bidi/match-route routes url))

(defn app-routes []
  (pushy/start! (pushy/pushy dispatch-route parse-url)))

(def url-for
  (partial bidi/path-for routes))
