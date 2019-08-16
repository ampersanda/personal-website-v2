(ns ^:figwheel-hooks ampersanda-personal.pages.home
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))

(defn template []
 [:main
  {:class "content editor"}
  [:h1 {:class "jumbo use-global-slant"} "Hello!"]
  [:h1 "My name is " [:span {:style {:color "var(--blue)"}} "Mochamad Lucky Pradana"] ". I am Front End Developer and (ocassionally) Flutter Developer who live in " [:a {:href "https://goo.gl/maps/sJumGDqZ8KVz16nG7"} "Surabaya, Indonesia"] "."]])
