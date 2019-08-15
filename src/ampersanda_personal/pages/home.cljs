(ns ^:figwheel-hooks ampersanda-personal.pages.home
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [reagent.core :as reagent]))

(defn template []
  [:nav
   [:a {:href "#" :id "logo"} "Ampersanda"] ;; animate using blotter or use SVG
   [:a {:href "#" :class "pointer-link pointer-link__bottom-left"} "Stories"]
   [:a {:href "#" :class "pointer-link pointer-link__bottom-right"} "Contact"]])
