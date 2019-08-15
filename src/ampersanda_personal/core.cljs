(ns ^:figwheel-hooks ampersanda-personal.core
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.pages.home :as home]
    [ampersanda-personal.pages.blog :as blog]
    [ampersanda-personal.pages.contact :as contact]
    [reagent.core :as reagent]))

; --------------------
(defmulti panels identity)

(defmethod panels :home-panel [] [home/template])
(defmethod panels :contact-panel [] [contact/template])
(defmethod panels :blog-panel [] [blog/template])
(defmethod panels :default [] [:div "404"])

(defn main-panel []
  (let [active-panel (:route @state/app-state)]
    (panels active-panel)))

(defn mount [el]
  (reagent/render-component [main-panel] el))

(defn mount-app-element []
  (routes/app-routes)
  (when-let [el (js/document.getElementById "app")]
    (mount el)))

(mount-app-element)

(defn ^:after-load on-reload []
  (mount-app-element))
