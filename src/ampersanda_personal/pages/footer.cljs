(ns ^:figwheel-hooks ampersanda-personal.pages.footer
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))

(defn template []
  [:div
   {:class "wrapper"}
   [:a
    (+pointer-event
     {:href  (routes/url-for :home)
      :class "pointer-link pointer-link__bottom-left"})
    "Home"]
   [:div
    {:class "editor text"}
    [:p
     "Created using "
     [:a
      (+pointer-event {:href "https://clojurescript.org"})
      "Clojurescript"]
     " + "
     [:a
      (+pointer-event {:href "https://reagent-project.github.io"})
      "Reagent"]
     " + "
     [:a
      (+pointer-event {:href "http://quil.info"})
      "Quil"]
     " +️"
     [:span {:style {:color :red}} "❤️"]]]
   [:a
    (+pointer-event
     {:href  (routes/url-for :contact)
      :class "pointer-link pointer-link__bottom-right"})
    "Contact"]])
