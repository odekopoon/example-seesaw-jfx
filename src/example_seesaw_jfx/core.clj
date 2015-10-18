(ns example-seesaw-jfx.core
  (:gen-class)
  (:require [seesaw.core :as sw])
  (:import [javafx.scene Group Scene]
           [javafx.scene.text Font Text]
           [javafx.scene.shape Circle Rectangle]
           javafx.scene.paint.Color
           javafx.application.Platform
           javafx.embed.swing.JFXPanel))

(defmacro jfx-run-later [& exprs]
  `(Platform/runLater (fn [] ~@exprs)))

(defn new-jfx-view [pane]
  (let [text (doto (Text. 100 100 "サンプル！")
               (.setFont (Font. 60))
               (.setUnderline true))
        rect (doto (Rectangle. 200.0 150.0 (Color/web "green"))
               (.setX 200)
               (.setY 150))
        circle (doto (Circle. 100.0 (Color/web "red" 0.6))
                 (.setCenterX 200)
                 (.setCenterY 220))
        root (Group.)]
    (doto (.getChildren root)
      (.add rect)
      (.add circle)
      (.add text))
    (.setScene pane (Scene. root Color/CYAN))))

(defn new-jframe [^JFXPanel pane]
  (sw/frame :title "Seesaw & JavaFX8 Example"
            :size [500 :by 500]
            :on-close :dispose
            :visible? true
            :content pane))

(defn -main []
  (sw/native!)
  (let [pane (JFXPanel.)]
    (jfx-run-later (new-jfx-view pane))
    (sw/invoke-later (new-jframe pane))))
