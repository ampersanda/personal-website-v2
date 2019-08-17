(ns ^:figwheel-hooks ampersanda-personal.pages.blog
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.utils.shim :refer [shim-scroll reset-scroll]]
    [reagent.core :as reagent]))

(defn template []
  (reagent/create-class
   {:component-did-mount
    (fn [_]
      ;; shim page scroll
      (shim-scroll))

    :component-will-unmount
    (fn []
      (reset-scroll))

    :reagent-render
    (fn []
      [:div
       (str "Hello from " (:text @state/app-state))
       [:div [:a {:href (routes/url-for :about)} "go to About Page"]]])}))
