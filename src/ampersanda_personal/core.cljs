(ns ^:figwheel-hooks ampersanda-personal.core
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.pages.home :as home]
    [ampersanda-personal.pages.blog :as blog]
    [ampersanda-personal.pages.nav :as nav]
    [ampersanda-personal.utils.shim :as shim]
    [ampersanda-personal.pages.footer :as footer]
    [ampersanda-personal.pages.contact :as contact]
    [ampersanda-personal.pages.projects :as projects]
    [ampersanda-personal.widget.cursor :as cursor-widget]
    [reagent.core :as reagent]))

; --------------------
(defmulti panels identity)

(defmethod panels :home-panel [] [home/template])
(defmethod panels :contact-panel [] [contact/template])
(defmethod panels :blog-panel [] [blog/template])
(defmethod panels :projects-panel [] [projects/template])
(defmethod panels :about-panel [] [:h1 {:class "jumbo"} "About"])
(defmethod panels :default [] [:h1 {:class "jumbo"} "404"])

(defn main-panel []
  (panels (:route @state/app-state)))

(defn mount [el]
  (reagent/render-component [main-panel] el))

(defn mount-app-element []
  (routes/app-routes)
  (cursor-widget/execute! js/window.innerWidth js/window.innerHeight)
  (when-let [app (js/document.getElementById "app")]
    (reagent/render-component [main-panel] app))
  (when-let [footer (js/document.querySelector "footer")]
    (reagent/render-component [footer/template] footer)))

(mount-app-element)

(defn ^:after-load on-reload []
  (mount-app-element))
