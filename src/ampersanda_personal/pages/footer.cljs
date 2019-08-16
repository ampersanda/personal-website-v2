(ns ^:figwheel-hooks ampersanda-personal.pages.footer
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))

(defn template []
  [:div {:class "wrapper"}
   [:a
    (+pointer-event
     {:href  (routes/url-for :blog)
      :class "pointer-link pointer-link__bottom-left"})
    "Stories"]
   [:p "Created using Clojurescript + Reagent + Quil +️"
    [:span {:style {:color :red}} "❤️"]]
   [:a
    (+pointer-event
     {:href  (routes/url-for :contact)
      :class "pointer-link pointer-link__bottom-right"})
    "Contact"]])
