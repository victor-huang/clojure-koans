(def test-address
  {:street-address "123 Test Lane"
   :city "Testerville"
   :state "TX"})

(meditations
  "Destructuring is an arbiter: it breaks up arguments"
  (= ":bar:foo" ((fn [[a b]] (str b a))
         [:foo :bar]))
;2
  "Whether in function definitions"
  (= (str "First comes love, "
          "then comes marriage, "
          "then comes Clojure with the baby carriage")
     ((fn [[a b c]]
        (str "First comes " a 
             ", then comes " b
             ", then comes " c
             " with the baby carriage"))
      ["love" "marriage" "Clojure"]))

  "Or in let expressionss"
  (= "Rich Hickey aka The Clojurer aka Go Time aka Macro Killah"
     (let [[first-name last-name & aliases]
           (list "Rich" "Hickey" "The Clojurer" "Go Time" "Macro Killah")]
       ;;;(println "-----------"  last-name "-------" aliases "----")
       ;;(println (str first-name " " last-name (reduce (fn [a b] (str a " aka " b ) ) "" aliases)))
       (str first-name " " last-name (reduce (fn [a b] (str a " aka " b ) ) "" aliases))))

  "You can regain the full argument if you like arguing"
  (= {:original-parts ["Steven" "Hawking"] :named-parts {:first "Steven" :last "Hawking"}}
     (let [[first-name last-name :as full-name] ["Steven" "Hawking"]]
       {:original-parts full-name :named-parts {:first first-name :last last-name}}))

  "Break up maps by key"
  (= "123 Test Lane, Testerville, TX"
     (let [{street-address :street-address, city :city, state :state} test-address]
       (str street-address ", " city ", " state)))

  "Or more succinctly"
  (= "123 Test Lane, Testerville, TX"
     (let [{:keys [street-address city state]} test-address]
       (str street-address ", " city ", " state)))
;;sdf
  "All together now!"
  (= "Test Testerson, 123 Test Lane, Testerville, TX"
     ((fn [[a b] {:keys [street-address city state]}]
          (apply str ( interpose ", " (list (str a " " b) street-address city state))))
       ["Test" "Testerson"] test-address)
  )

;  (= "Test Testerson, 123 Test Lane, Testerville, TX"
;     ((fn[[a b] {:keys [street-address city state]}] 
;        (apply str (interpose ", " (list (str a " " b) street-address city state))) 
;      ["Test" "Testerson"] test-address))
;  )
  
  
)