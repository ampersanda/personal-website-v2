(ns ^:figwheel-hooks ampersanda-personal.pages.contact
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.utils.shim :refer [render-with-shim]]
    [reagent.core :as reagent]))

(defn template []
  (render-with-shim
   [:div
    (str "Hello from " (:text @state/app-state))
    [:div [:a {:href (routes/url-for :about)} "go to About Page"]]]))
