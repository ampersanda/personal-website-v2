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
   [:div {:class "grid-menu--item" :style {:background "var(--blue)"}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#" :class "use-global-slant"} "Stories"]]]
   [:div {:class "grid-menu--item" :style {:background :coral}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#" :class "use-global-slant"} "Projects"]]]
   [:div {:class "grid-menu--item grid-menu--item__small grid-menu--item__square" :style {:background "#ed4d8b"}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#" :class "use-global-slant"} "About"]]]
   [:div {:class "grid-menu--item grid-menu--item__small grid-menu--item__square" :style {:background "#ebedf9"}}
    [:h1 {:class "grid-menu--title"} [:a {:href "#" :class "use-global-slant"} "Stories"]]]]])
