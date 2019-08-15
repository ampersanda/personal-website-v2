(ns ^:figwheel-hooks ampersanda-personal.pages.blog
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [reagent.core :as reagent]))

(defn template []
  [:div
   (str "Hello from " (:text @state/app-state))
   [:div [:a {:href (routes/url-for :about)} "go to About Page"]]])
