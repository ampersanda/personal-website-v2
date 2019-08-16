(ns ^:figwheel-hooks ampersanda-personal.core
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.pages.home :as home]
    [ampersanda-personal.pages.blog :as blog]
    [ampersanda-personal.pages.nav :as nav]
    [ampersanda-personal.pages.footer :as footer]
    [ampersanda-personal.pages.contact :as contact]
    [ampersanda-personal.widget.cursor :as cursor-widget]
    [reagent.core :as reagent]))

; --------------------
(defmulti panels identity)

(defmethod panels :home-panel [] [home/template])
(defmethod panels :contact-panel [] [contact/template])
(defmethod panels :blog-panel [] [blog/template])
(defmethod panels :default [] [:h1 {:class "jumbo"} "404"])

(defn main-panel []
  (let [active-panel (:route @state/app-state)]
    [:div
     [nav/template]
     (panels active-panel)]))

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
