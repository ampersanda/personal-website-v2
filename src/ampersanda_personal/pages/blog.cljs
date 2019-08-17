(ns ^:figwheel-hooks ampersanda-personal.pages.blog
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [reagent.core :as reagent]
    [ampersanda-personal.utils.shim :refer [render-with-shim]]))

(defn template []
  (render-with-shim
   [:div
    [:div [:a {:href (routes/url-for :home)} "HOme"]]]))
