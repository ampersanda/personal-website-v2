(ns ^:figwheel-hooks ampersanda-personal.pages.projects
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [reagent.core :as reagent]
    [ampersanda-personal.utils.shim :refer [render-with-shim]]))

(defn template []
  (render-with-shim
   [:main
    {:class "content editor"}
    [:h1 {:class "jumbo use-global-slant"} "Projects"]

    [:div
     {:class "grid-menu normal-cursor"}
     [:a
      {:class "grid-menu--item"
       :style {:background            "#888 url('images/lifestone-phone.jpg')"
               :background-blend-mode :multiply
               :background-position   "bottom right"
               :background-repeat     "no-repeat"
               :background-size       :cover}
       :href  (routes/url-for :blog)}
      [:h1 {:class "grid-menu--title" :style {:color :white}} "Lifestone Indonesia"]]
     [:a
      {:class "grid-menu--item"
       :style {:background :coral}
       :href  (routes/url-for :projects)}
      [:h1 {:class "grid-menu--title"} "Projects"]]
     [:a
      {:class "grid-menu--item grid-menu--item__small grid-menu--item__square"
       :style {:background "#ed4d8b"}
       :href  (routes/url-for :about)}
      [:h1 {:class "grid-menu--title"} "About"]]
     [:a
      {:class "grid-menu--item grid-menu--item__small grid-menu--item__square"
       :style {:background "#ebedf9"}
       :href  (routes/url-for :contact)}
      [:h1 {:class "grid-menu--title"} "Contact"]]]]))
