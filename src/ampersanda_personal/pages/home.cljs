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
  [:h1 "My name is " [:span {:style {:color "var(--blue)"}} "Mochamad Lucky Pradana"] ". I am Front End Developer and (ocassionally) Flutter Developer who live in " [:a {:href "https://goo.gl/maps/sJumGDqZ8KVz16nG7"} "Surabaya, Indonesia"] "."]

  [:div {:class "grid-menu normal-cursor"}
   [:div {:class "grid-menu--item" :style {:background :blue}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#"} "Stories"]]]
   [:div {:class "grid-menu--item" :style {:background :red}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#"} "Projects"]]]
   [:div {:class "grid-menu--item grid-menu--item__small grid-menu--item__square" :style {:background :green}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#"} "About"]]]
   [:div {:class "grid-menu--item grid-menu--item__small grid-menu--item__square" :style {:background :purple}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#"} "Stories"]]]]])
