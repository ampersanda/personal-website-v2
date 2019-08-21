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
      (+pointer-event {:href "https://clojurescript.org" :target "_blank"})
      "Clojurescript"]
     " + "
     [:a
      (+pointer-event {:href "https://reagent-project.github.io" :target "_blank"})
      "Reagent"]
     " + "
     [:a
      (+pointer-event {:href "http://quil.info" :target "_blank"})
      "Quil"]
     " +️"
     [:span {:style {:color :red}} "❤️"]]]
   [:button
    (+pointer-event
     {:on-click (fn [e]
                 (.preventDefault e)
                 (js/window.scrollTo 0 0))
      :class "pointer-link pointer-link__bottom-right"})
    [:i {:class "icon ion-ios-arrow-up"}]
    "  Back to top"]])
