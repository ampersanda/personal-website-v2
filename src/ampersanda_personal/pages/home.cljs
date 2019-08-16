(ns ^:figwheel-hooks ampersanda-personal.pages.home
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))

(defn template []
  [:nav
   [:a {:href "#" :id "logo"} "Ampersanda"]
   ;; animate using blotter or use SVG
   [:a
    (+pointer-event
     {:href  "#"
      :class "pointer-link pointer-link__bottom-left"})
    "Stories"]
   [:a
    (+pointer-event
     {:href "#" :class "pointer-link pointer-link__bottom-right"})
    "Contact"]])
