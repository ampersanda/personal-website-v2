(ns ^:figwheel-hooks ampersanda-personal.core
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [reagent.core :as reagent]))

(defn home-panel []
  [:div
   (str "Hello from " (:name @state/app-state) ". This is the Home Page.")
   [:div [:a {:href (routes/url-for :about)} "go to About Page"]]])

(defn about-panel []
  [:div
   "This is the About Page."
   [:div [:a {:href (routes/url-for :home)} "go to Home Page"]]])

; --------------------
(defmulti panels identity)

(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
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
