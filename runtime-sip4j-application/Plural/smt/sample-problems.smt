; Constraints tested in FractionTest and ModifyingIterators

; Results for test
(benchmark plural0
; [0 < const2, 0 < const3, <=[const2, const1, const0], 0 < VAR2, 0 < VAR3, <=[VAR2, VAR1, VAR0], const0 == VAR0 + VAR4, const1 == VAR1 + VAR5, const2 == VAR2 + VAR6, const3 == VAR3 + VAR7, <=[VAR6, VAR5, VAR4]]
  :status unsat
  :extrafuns ((const2 Real))
  :extrafuns ((const3 Real))
  :extrafuns ((const1 Real))
  :extrafuns ((const0 Real))
  :assumption (and (< 0.0 const2) (< 0.0 const3) (<= const2 const1) (<= const1 const0) (<= 0.0 const2) (<= const2 1.0) (<= 0.0 const3) (<= const3 1.0) (<= 0.0 const1) (<= const1 1.0) (<= 0.0 const0) (<= const0 1.0))
  :formula (not (implies (and (< 0.0 const2) (< 0.0 const3) (<= const2 const1) (<= const1 const0)) (exists (?VAR2 Real) (?VAR3 Real) (?VAR1 Real) (?VAR0 Real) (?VAR4 Real) (?VAR5 Real) (?VAR6 Real) (?VAR7 Real) (and (< 0.0 ?VAR2) (< 0.0 ?VAR3) (<= ?VAR2 ?VAR1) (<= ?VAR1 ?VAR0) (= const0 (+ ?VAR0 ?VAR4)) (= const1 (+ ?VAR1 ?VAR5)) (= const2 (+ ?VAR2 ?VAR6)) (= const3 (+ ?VAR3 ?VAR7)) (<= ?VAR6 ?VAR5) (<= ?VAR5 ?VAR4) (<= 0.0 ?VAR2) (<= ?VAR2 1.0) (<= 0.0 ?VAR3) (<= ?VAR3 1.0) (<= 0.0 ?VAR1) (<= ?VAR1 1.0) (<= 0.0 ?VAR0) (<= ?VAR0 1.0) (<= 0.0 ?VAR4) (<= ?VAR4 1.0) (<= 0.0 ?VAR5) (<= ?VAR5 1.0) (<= 0.0 ?VAR6) (<= ?VAR6 1.0) (<= 0.0 ?VAR7) (<= ?VAR7 1.0)))))
)

(benchmark plural1
; [0 < const2, 0 < const3, <=[const2, const1, const0], 0 < VAR2, 0 < VAR3, <=[VAR2, VAR1, VAR0], const0 == VAR0 + VAR4, const1 == VAR1 + VAR5, const2 == VAR2 + VAR6, const3 == VAR3 + VAR7, <=[VAR6, VAR5, VAR4], 0 < VAR10, 0 < VAR11, <=[VAR10, VAR9, VAR8], VAR4 == VAR12 + VAR8, VAR5 == VAR13 + VAR9, VAR6 == VAR10 + VAR14, VAR7 == VAR11 + VAR15, <=[VAR14, VAR13, VAR12]]
  :status unsat
  :extrafuns ((const2 Real))
  :extrafuns ((const3 Real))
  :extrafuns ((const1 Real))
  :extrafuns ((const0 Real))
  :assumption (and (< 0.0 const2) (< 0.0 const3) (<= const2 const1) (<= const1 const0) (<= 0.0 const2) (<= const2 1.0) (<= 0.0 const3) (<= const3 1.0) (<= 0.0 const1) (<= const1 1.0) (<= 0.0 const0) (<= const0 1.0))
  :formula (not (implies (and (< 0.0 const2) (< 0.0 const3) (<= const2 const1) (<= const1 const0)) (exists (?VAR2 Real) (?VAR3 Real) (?VAR1 Real) (?VAR0 Real) (?VAR4 Real) (?VAR5 Real) (?VAR6 Real) (?VAR7 Real) (?VAR10 Real) (?VAR11 Real) (?VAR9 Real) (?VAR8 Real) (?VAR12 Real) (?VAR13 Real) (?VAR14 Real) (?VAR15 Real) (and (< 0.0 ?VAR2) (< 0.0 ?VAR3) (<= ?VAR2 ?VAR1) (<= ?VAR1 ?VAR0) (= const0 (+ ?VAR0 ?VAR4)) (= const1 (+ ?VAR1 ?VAR5)) (= const2 (+ ?VAR2 ?VAR6)) (= const3 (+ ?VAR3 ?VAR7)) (<= ?VAR6 ?VAR5) (<= ?VAR5 ?VAR4) (< 0.0 ?VAR10) (< 0.0 ?VAR11) (<= ?VAR10 ?VAR9) (<= ?VAR9 ?VAR8) (= ?VAR4 (+ ?VAR12 ?VAR8)) (= ?VAR5 (+ ?VAR13 ?VAR9)) (= ?VAR6 (+ ?VAR10 ?VAR14)) (= ?VAR7 (+ ?VAR11 ?VAR15)) (<= ?VAR14 ?VAR13) (<= ?VAR13 ?VAR12) (<= 0.0 ?VAR2) (<= ?VAR2 1.0) (<= 0.0 ?VAR3) (<= ?VAR3 1.0) (<= 0.0 ?VAR1) (<= ?VAR1 1.0) (<= 0.0 ?VAR0) (<= ?VAR0 1.0) (<= 0.0 ?VAR4) (<= ?VAR4 1.0) (<= 0.0 ?VAR5) (<= ?VAR5 1.0) (<= 0.0 ?VAR6) (<= ?VAR6 1.0) (<= 0.0 ?VAR7) (<= ?VAR7 1.0) (<= 0.0 ?VAR10) (<= ?VAR10 1.0) (<= 0.0 ?VAR11) (<= ?VAR11 1.0) (<= 0.0 ?VAR9) (<= ?VAR9 1.0) (<= 0.0 ?VAR8) (<= ?VAR8 1.0) (<= 0.0 ?VAR12) (<= ?VAR12 1.0) (<= 0.0 ?VAR13) (<= ?VAR13 1.0) (<= 0.0 ?VAR14) (<= ?VAR14 1.0) (<= 0.0 ?VAR15) (<= ?VAR15 1.0)))))
)

(benchmark plural2
; [0 < const2, 0 < const3, <=[const2, const1, const0], 0 < VAR2, 0 < VAR3, <=[VAR2, VAR1, VAR0], const0 == VAR0 + VAR4, const1 == VAR1 + VAR5, const2 == VAR2 + VAR6, const3 == VAR3 + VAR7, <=[VAR6, VAR5, VAR4], 0 < VAR10, 0 < VAR11, <=[VAR10, VAR9, VAR8], VAR4 == VAR12 + VAR8, VAR5 == VAR13 + VAR9, VAR6 == VAR10 + VAR14, VAR7 == VAR11 + VAR15, <=[VAR14, VAR13, VAR12], 0 < VAR18, 0 < VAR19, <=[VAR18, VAR17, VAR16], VAR12 == VAR16 + VAR20, VAR13 == VAR17 + VAR21, VAR14 == VAR18 + VAR22, VAR15 == VAR19 + VAR23, <=[VAR22, VAR21, VAR20]]
  :status unsat
  :extrafuns ((const2 Real))
  :extrafuns ((const3 Real))
  :extrafuns ((const1 Real))
  :extrafuns ((const0 Real))
  :assumption (and (< 0.0 const2) (< 0.0 const3) (<= const2 const1) (<= const1 const0) (<= 0.0 const2) (<= const2 1.0) (<= 0.0 const3) (<= const3 1.0) (<= 0.0 const1) (<= const1 1.0) (<= 0.0 const0) (<= const0 1.0))
  :formula (not (implies (and (< 0.0 const2) (< 0.0 const3) (<= const2 const1) (<= const1 const0)) (exists (?VAR2 Real) (?VAR3 Real) (?VAR1 Real) (?VAR0 Real) (?VAR4 Real) (?VAR5 Real) (?VAR6 Real) (?VAR7 Real) (?VAR10 Real) (?VAR11 Real) (?VAR9 Real) (?VAR8 Real) (?VAR12 Real) (?VAR13 Real) (?VAR14 Real) (?VAR15 Real) (?VAR18 Real) (?VAR19 Real) (?VAR17 Real) (?VAR16 Real) (?VAR20 Real) (?VAR21 Real) (?VAR22 Real) (?VAR23 Real) (and (< 0.0 ?VAR2) (< 0.0 ?VAR3) (<= ?VAR2 ?VAR1) (<= ?VAR1 ?VAR0) (= const0 (+ ?VAR0 ?VAR4)) (= const1 (+ ?VAR1 ?VAR5)) (= const2 (+ ?VAR2 ?VAR6)) (= const3 (+ ?VAR3 ?VAR7)) (<= ?VAR6 ?VAR5) (<= ?VAR5 ?VAR4) (< 0.0 ?VAR10) (< 0.0 ?VAR11) (<= ?VAR10 ?VAR9) (<= ?VAR9 ?VAR8) (= ?VAR4 (+ ?VAR12 ?VAR8)) (= ?VAR5 (+ ?VAR13 ?VAR9)) (= ?VAR6 (+ ?VAR10 ?VAR14)) (= ?VAR7 (+ ?VAR11 ?VAR15)) (<= ?VAR14 ?VAR13) (<= ?VAR13 ?VAR12) (< 0.0 ?VAR18) (< 0.0 ?VAR19) (<= ?VAR18 ?VAR17) (<= ?VAR17 ?VAR16) (= ?VAR12 (+ ?VAR16 ?VAR20)) (= ?VAR13 (+ ?VAR17 ?VAR21)) (= ?VAR14 (+ ?VAR18 ?VAR22)) (= ?VAR15 (+ ?VAR19 ?VAR23)) (<= ?VAR22 ?VAR21) (<= ?VAR21 ?VAR20) (<= 0.0 ?VAR2) (<= ?VAR2 1.0) (<= 0.0 ?VAR3) (<= ?VAR3 1.0) (<= 0.0 ?VAR1) (<= ?VAR1 1.0) (<= 0.0 ?VAR0) (<= ?VAR0 1.0) (<= 0.0 ?VAR4) (<= ?VAR4 1.0) (<= 0.0 ?VAR5) (<= ?VAR5 1.0) (<= 0.0 ?VAR6) (<= ?VAR6 1.0) (<= 0.0 ?VAR7) (<= ?VAR7 1.0) (<= 0.0 ?VAR10) (<= ?VAR10 1.0) (<= 0.0 ?VAR11) (<= ?VAR11 1.0) (<= 0.0 ?VAR9) (<= ?VAR9 1.0) (<= 0.0 ?VAR8) (<= ?VAR8 1.0) (<= 0.0 ?VAR12) (<= ?VAR12 1.0) (<= 0.0 ?VAR13) (<= ?VAR13 1.0) (<= 0.0 ?VAR14) (<= ?VAR14 1.0) (<= 0.0 ?VAR15) (<= ?VAR15 1.0) (<= 0.0 ?VAR18) (<= ?VAR18 1.0) (<= 0.0 ?VAR19) (<= ?VAR19 1.0) (<= 0.0 ?VAR17) (<= ?VAR17 1.0) (<= 0.0 ?VAR16) (<= ?VAR16 1.0) (<= 0.0 ?VAR20) (<= ?VAR20 1.0) (<= 0.0 ?VAR21) (<= ?VAR21 1.0) (<= 0.0 ?VAR22) (<= ?VAR22 1.0) (<= 0.0 ?VAR23) (<= ?VAR23 1.0)))))
)

; Results for borrowError
(benchmark plural3
; [0 < const6, 0 < const7, <=[const6, const5, const4], 0 < VAR26, 0 < VAR27, <=[VAR26, VAR25, VAR24], const4 == VAR24 + VAR28, const5 == VAR25 + VAR29, const6 == VAR26 + VAR30, const7 == VAR27 + VAR31, <=[VAR30, VAR29, VAR28]]
  :status unsat
  :extrafuns ((const6 Real))
  :extrafuns ((const7 Real))
  :extrafuns ((const5 Real))
  :extrafuns ((const4 Real))
  :assumption (and (< 0.0 const6) (< 0.0 const7) (<= const6 const5) (<= const5 const4) (<= 0.0 const6) (<= const6 1.0) (<= 0.0 const7) (<= const7 1.0) (<= 0.0 const5) (<= const5 1.0) (<= 0.0 const4) (<= const4 1.0))
  :formula (not (implies (and (< 0.0 const6) (< 0.0 const7) (<= const6 const5) (<= const5 const4)) (exists (?VAR26 Real) (?VAR27 Real) (?VAR25 Real) (?VAR24 Real) (?VAR28 Real) (?VAR29 Real) (?VAR30 Real) (?VAR31 Real) (and (< 0.0 ?VAR26) (< 0.0 ?VAR27) (<= ?VAR26 ?VAR25) (<= ?VAR25 ?VAR24) (= const4 (+ ?VAR24 ?VAR28)) (= const5 (+ ?VAR25 ?VAR29)) (= const6 (+ ?VAR26 ?VAR30)) (= const7 (+ ?VAR27 ?VAR31)) (<= ?VAR30 ?VAR29) (<= ?VAR29 ?VAR28) (<= 0.0 ?VAR26) (<= ?VAR26 1.0) (<= 0.0 ?VAR27) (<= ?VAR27 1.0) (<= 0.0 ?VAR25) (<= ?VAR25 1.0) (<= 0.0 ?VAR24) (<= ?VAR24 1.0) (<= 0.0 ?VAR28) (<= ?VAR28 1.0) (<= 0.0 ?VAR29) (<= ?VAR29 1.0) (<= 0.0 ?VAR30) (<= ?VAR30 1.0) (<= 0.0 ?VAR31) (<= ?VAR31 1.0)))))
)

(benchmark plural4
; [0 < const6, 0 < const7, <=[const6, const5, const4], 0 < VAR26, 0 < VAR27, <=[VAR26, VAR25, VAR24], const4 == VAR24 + VAR28, const5 == VAR25 + VAR29, const6 == VAR26 + VAR30, const7 == VAR27 + VAR31, <=[VAR30, VAR29, VAR28], VAR28 == const4 + VAR32, VAR29 == const5 + VAR33, VAR30 == const6 + VAR34, VAR31 == const7 + VAR35, <=[VAR34, VAR33, VAR32]]
  :status sat
  :extrafuns ((const6 Real))
  :extrafuns ((const7 Real))
  :extrafuns ((const5 Real))
  :extrafuns ((const4 Real))
  :assumption (and (< 0.0 const6) (< 0.0 const7) (<= const6 const5) (<= const5 const4) (<= 0.0 const6) (<= const6 1.0) (<= 0.0 const7) (<= const7 1.0) (<= 0.0 const5) (<= const5 1.0) (<= 0.0 const4) (<= const4 1.0))
  :formula (not (implies (and (< 0.0 const6) (< 0.0 const7) (<= const6 const5) (<= const5 const4)) (exists (?VAR26 Real) (?VAR27 Real) (?VAR25 Real) (?VAR24 Real) (?VAR28 Real) (?VAR29 Real) (?VAR30 Real) (?VAR31 Real) (?VAR32 Real) (?VAR33 Real) (?VAR34 Real) (?VAR35 Real) (and (< 0.0 ?VAR26) (< 0.0 ?VAR27) (<= ?VAR26 ?VAR25) (<= ?VAR25 ?VAR24) (= const4 (+ ?VAR24 ?VAR28)) (= const5 (+ ?VAR25 ?VAR29)) (= const6 (+ ?VAR26 ?VAR30)) (= const7 (+ ?VAR27 ?VAR31)) (<= ?VAR30 ?VAR29) (<= ?VAR29 ?VAR28) (= ?VAR28 (+ const4 ?VAR32)) (= ?VAR29 (+ const5 ?VAR33)) (= ?VAR30 (+ const6 ?VAR34)) (= ?VAR31 (+ const7 ?VAR35)) (<= ?VAR34 ?VAR33) (<= ?VAR33 ?VAR32) (<= 0.0 ?VAR26) (<= ?VAR26 1.0) (<= 0.0 ?VAR27) (<= ?VAR27 1.0) (<= 0.0 ?VAR25) (<= ?VAR25 1.0) (<= 0.0 ?VAR24) (<= ?VAR24 1.0) (<= 0.0 ?VAR28) (<= ?VAR28 1.0) (<= 0.0 ?VAR29) (<= ?VAR29 1.0) (<= 0.0 ?VAR30) (<= ?VAR30 1.0) (<= 0.0 ?VAR31) (<= ?VAR31 1.0) (<= 0.0 ?VAR32) (<= ?VAR32 1.0) (<= 0.0 ?VAR33) (<= ?VAR33 1.0) (<= 0.0 ?VAR34) (<= ?VAR34 1.0) (<= 0.0 ?VAR35) (<= ?VAR35 1.0)))))
)

; Results for consumeRoot
(benchmark plural5
; [0 < const8, 0 < const9]
  :status unsat
  :extrafuns ((const8 Real))
  :extrafuns ((const9 Real))
  :assumption (and (< 0.0 const8) (< 0.0 const9) (<= 0.0 const8) (<= const8 1.0) (<= 0.0 const9) (<= const9 1.0))
  :formula (not (implies (and (< 0.0 const8) (< 0.0 const9)) true))
)

; Results for rootBorrowError
(benchmark plural6
; [0 < const10, 0 < const11, 0 < VAR36, 0 < VAR37, const10 == VAR36 + VAR38, const11 == VAR37 + VAR39]
  :status unsat
  :extrafuns ((const10 Real))
  :extrafuns ((const11 Real))
  :assumption (and (< 0.0 const10) (< 0.0 const11) (<= 0.0 const10) (<= const10 1.0) (<= 0.0 const11) (<= const11 1.0))
  :formula (not (implies (and (< 0.0 const10) (< 0.0 const11)) (exists (?VAR36 Real) (?VAR37 Real) (?VAR38 Real) (?VAR39 Real) (and (< 0.0 ?VAR36) (< 0.0 ?VAR37) (= const10 (+ ?VAR36 ?VAR38)) (= const11 (+ ?VAR37 ?VAR39)) (<= 0.0 ?VAR36) (<= ?VAR36 1.0) (<= 0.0 ?VAR37) (<= ?VAR37 1.0) (<= 0.0 ?VAR38) (<= ?VAR38 1.0) (<= 0.0 ?VAR39) (<= ?VAR39 1.0)))))
)

(benchmark plural7
; [0 < const10, 0 < const11, 0 < VAR36, 0 < VAR37, const10 == VAR36 + VAR38, const11 == VAR37 + VAR39, VAR38 == const10 + VAR40, VAR39 == const11 + VAR41]
  :status sat
  :extrafuns ((const10 Real))
  :extrafuns ((const11 Real))
  :assumption (and (< 0.0 const10) (< 0.0 const11) (<= 0.0 const10) (<= const10 1.0) (<= 0.0 const11) (<= const11 1.0))
  :formula (not (implies (and (< 0.0 const10) (< 0.0 const11)) (exists (?VAR36 Real) (?VAR37 Real) (?VAR38 Real) (?VAR39 Real) (?VAR40 Real) (?VAR41 Real) (and (< 0.0 ?VAR36) (< 0.0 ?VAR37) (= const10 (+ ?VAR36 ?VAR38)) (= const11 (+ ?VAR37 ?VAR39)) (= ?VAR38 (+ const10 ?VAR40)) (= ?VAR39 (+ const11 ?VAR41)) (<= 0.0 ?VAR36) (<= ?VAR36 1.0) (<= 0.0 ?VAR37) (<= ?VAR37 1.0) (<= 0.0 ?VAR38) (<= ?VAR38 1.0) (<= 0.0 ?VAR39) (<= ?VAR39 1.0) (<= 0.0 ?VAR40) (<= ?VAR40 1.0) (<= 0.0 ?VAR41) (<= ?VAR41 1.0)))))
)

; Results for ModifyingIterator
(benchmark plural8
; [0 < 1, 1 == 1 + VAR42, 1 == 1 + VAR43]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR42 Real) (?VAR43 Real) (and (= 1.0 (+ 1.0 ?VAR42)) (= 1.0 (+ 1.0 ?VAR43)) (<= 0.0 ?VAR42) (<= ?VAR42 1.0) (<= 0.0 ?VAR43) (<= ?VAR43 1.0)))))
)

(benchmark plural9
; [0 < const12]
  :status unsat
  :extrafuns ((const12 Real))
  :assumption (and (< 0.0 const12) (<= 0.0 const12) (<= const12 1.0))
  :formula (not (implies (< 0.0 const12) true))
)

; Results for hasNext
(benchmark plural10
; []
  :status unsat
  :assumption true
  :formula (not (implies true true))
)

(benchmark plural11
; [0 < const13]
  :status unsat
  :extrafuns ((const13 Real))
  :assumption (and (< 0.0 const13) (<= 0.0 const13) (<= const13 1.0))
  :formula (not (implies (< 0.0 const13) true))
)

(benchmark plural12
; [0 < const13, const13 == const13 + VAR44, 0 == 0 + VAR45]
  :status unsat
  :extrafuns ((const13 Real))
  :assumption (and (< 0.0 const13) (<= 0.0 const13) (<= const13 1.0))
  :formula (not (implies (< 0.0 const13) (exists (?VAR44 Real) (?VAR45 Real) (and (= const13 (+ const13 ?VAR44)) (= 0.0 (+ 0.0 ?VAR45)) (<= 0.0 ?VAR44) (<= ?VAR44 1.0) (<= 0.0 ?VAR45) (<= ?VAR45 1.0)))))
)

; Results for next
(benchmark plural13
; [0 < const14, 1 == 1, 1 == VAR46, VAR50 <= VAR49, 1 == VAR47 + VAR49, 1 == VAR48 + VAR50, <=[VAR48, VAR47, VAR46], 0 < VAR48]
  :status unsat
  :extrafuns ((const14 Real))
  :assumption (and (< 0.0 const14) (= 1.0 1.0) (<= 0.0 const14) (<= const14 1.0))
  :formula (not (implies (and (< 0.0 const14) (= 1.0 1.0)) (exists (?VAR46 Real) (?VAR50 Real) (?VAR49 Real) (?VAR47 Real) (?VAR48 Real) (and (= 1.0 ?VAR46) (<= ?VAR50 ?VAR49) (= 1.0 (+ ?VAR47 ?VAR49)) (= 1.0 (+ ?VAR48 ?VAR50)) (<= ?VAR48 ?VAR47) (<= ?VAR47 ?VAR46) (< 0.0 ?VAR48) (<= 0.0 ?VAR46) (<= ?VAR46 1.0) (<= 0.0 ?VAR50) (<= ?VAR50 1.0) (<= 0.0 ?VAR49) (<= ?VAR49 1.0) (<= 0.0 ?VAR47) (<= ?VAR47 1.0) (<= 0.0 ?VAR48) (<= ?VAR48 1.0)))))
)

(benchmark plural14
; [0 < const14, 1 == 1, 1 == VAR46, VAR50 <= VAR49, 1 == VAR47 + VAR49, 1 == VAR48 + VAR50, <=[VAR48, VAR47, VAR46], 0 < VAR48, 0 < VAR50, 1 == const14 + VAR55, 1 == 1 + VAR56]
  :status unsat
  :extrafuns ((const14 Real))
  :assumption (and (< 0.0 const14) (= 1.0 1.0) (<= 0.0 const14) (<= const14 1.0))
  :formula (not (implies (and (< 0.0 const14) (= 1.0 1.0)) (exists (?VAR46 Real) (?VAR50 Real) (?VAR49 Real) (?VAR47 Real) (?VAR48 Real) (?VAR55 Real) (?VAR56 Real) (and (= 1.0 ?VAR46) (<= ?VAR50 ?VAR49) (= 1.0 (+ ?VAR47 ?VAR49)) (= 1.0 (+ ?VAR48 ?VAR50)) (<= ?VAR48 ?VAR47) (<= ?VAR47 ?VAR46) (< 0.0 ?VAR48) (< 0.0 ?VAR50) (= 1.0 (+ const14 ?VAR55)) (= 1.0 (+ 1.0 ?VAR56)) (<= 0.0 ?VAR46) (<= ?VAR46 1.0) (<= 0.0 ?VAR50) (<= ?VAR50 1.0) (<= 0.0 ?VAR49) (<= ?VAR49 1.0) (<= 0.0 ?VAR47) (<= ?VAR47 1.0) (<= 0.0 ?VAR48) (<= ?VAR48 1.0) (<= 0.0 ?VAR55) (<= ?VAR55 1.0) (<= 0.0 ?VAR56) (<= ?VAR56 1.0)))))
)

; Results for remove
(benchmark plural15
; [0 < const16, const16 <= const15]
  :status unsat
  :extrafuns ((const16 Real))
  :extrafuns ((const15 Real))
  :assumption (and (< 0.0 const16) (<= const16 const15) (<= 0.0 const16) (<= const16 1.0) (<= 0.0 const15) (<= const15 1.0))
  :formula (not (implies (and (< 0.0 const16) (<= const16 const15)) true))
)

(benchmark plural16
; [0 < const16, const16 <= const15, const15 == const15 + VAR57, const16 == const16 + VAR58, 1 == 1 + VAR59, VAR58 <= VAR57]
  :status unsat
  :extrafuns ((const16 Real))
  :extrafuns ((const15 Real))
  :assumption (and (< 0.0 const16) (<= const16 const15) (<= 0.0 const16) (<= const16 1.0) (<= 0.0 const15) (<= const15 1.0))
  :formula (not (implies (and (< 0.0 const16) (<= const16 const15)) (exists (?VAR57 Real) (?VAR58 Real) (?VAR59 Real) (and (= const15 (+ const15 ?VAR57)) (= const16 (+ const16 ?VAR58)) (= 1.0 (+ 1.0 ?VAR59)) (<= ?VAR58 ?VAR57) (<= 0.0 ?VAR57) (<= ?VAR57 1.0) (<= 0.0 ?VAR58) (<= ?VAR58 1.0) (<= 0.0 ?VAR59) (<= ?VAR59 1.0)))))
)

; Results for createList
(benchmark plural17
; []
  :status unsat
  :assumption true
  :formula (not (implies true true))
)

; Results for arrayTest
(benchmark plural18
; [0 < 1, 0 < VAR63, 1 == VAR63 + VAR64, 1 == 0 + VAR65, VAR66 == VAR63 + VAR64, VAR63 + VAR64 <= 1, VAR67 == 0 + VAR65, 0 + VAR65 <= 1, 0 < VAR68, VAR66 == VAR68 + VAR69, VAR67 == 1 + VAR70, VAR71 == VAR68 + VAR69, VAR68 + VAR69 <= 1, VAR72 == 1 + VAR70, 1 + VAR70 <= 1, 0 < VAR74, VAR74 <= VAR73, 1 == VAR72, VAR78 <= VAR77, 1 == VAR75 + VAR77, 1 == VAR76 + VAR78, VAR76 <= VAR75, VAR75 == VAR73 + VAR79, VAR76 == VAR74 + VAR80, 1 == 1 + VAR81, VAR80 <= VAR79, VAR82 == VAR73 + VAR79, VAR73 + VAR79 <= 1, VAR83 == VAR74 + VAR80, VAR74 + VAR80 <= 1, VAR84 == 1 + VAR81, 1 + VAR81 <= 1, VAR83 <= VAR82]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR63 Real) (?VAR64 Real) (?VAR65 Real) (?VAR66 Real) (?VAR67 Real) (?VAR68 Real) (?VAR69 Real) (?VAR70 Real) (?VAR71 Real) (?VAR72 Real) (?VAR74 Real) (?VAR73 Real) (?VAR78 Real) (?VAR77 Real) (?VAR75 Real) (?VAR76 Real) (?VAR79 Real) (?VAR80 Real) (?VAR81 Real) (?VAR82 Real) (?VAR83 Real) (?VAR84 Real) (and (< 0.0 ?VAR63) (= 1.0 (+ ?VAR63 ?VAR64)) (= 1.0 (+ 0.0 ?VAR65)) (= ?VAR66 (+ ?VAR63 ?VAR64)) (<= (+ ?VAR63 ?VAR64) 1.0) (= ?VAR67 (+ 0.0 ?VAR65)) (<= (+ 0.0 ?VAR65) 1.0) (< 0.0 ?VAR68) (= ?VAR66 (+ ?VAR68 ?VAR69)) (= ?VAR67 (+ 1.0 ?VAR70)) (= ?VAR71 (+ ?VAR68 ?VAR69)) (<= (+ ?VAR68 ?VAR69) 1.0) (= ?VAR72 (+ 1.0 ?VAR70)) (<= (+ 1.0 ?VAR70) 1.0) (< 0.0 ?VAR74) (<= ?VAR74 ?VAR73) (= 1.0 ?VAR72) (<= ?VAR78 ?VAR77) (= 1.0 (+ ?VAR75 ?VAR77)) (= 1.0 (+ ?VAR76 ?VAR78)) (<= ?VAR76 ?VAR75) (= ?VAR75 (+ ?VAR73 ?VAR79)) (= ?VAR76 (+ ?VAR74 ?VAR80)) (= 1.0 (+ 1.0 ?VAR81)) (<= ?VAR80 ?VAR79) (= ?VAR82 (+ ?VAR73 ?VAR79)) (<= (+ ?VAR73 ?VAR79) 1.0) (= ?VAR83 (+ ?VAR74 ?VAR80)) (<= (+ ?VAR74 ?VAR80) 1.0) (= ?VAR84 (+ 1.0 ?VAR81)) (<= (+ 1.0 ?VAR81) 1.0) (<= ?VAR83 ?VAR82) (<= 0.0 ?VAR63) (<= ?VAR63 1.0) (<= 0.0 ?VAR64) (<= ?VAR64 1.0) (<= 0.0 ?VAR65) (<= ?VAR65 1.0) (<= 0.0 ?VAR66) (<= ?VAR66 1.0) (<= 0.0 ?VAR67) (<= ?VAR67 1.0) (<= 0.0 ?VAR68) (<= ?VAR68 1.0) (<= 0.0 ?VAR69) (<= ?VAR69 1.0) (<= 0.0 ?VAR70) (<= ?VAR70 1.0) (<= 0.0 ?VAR71) (<= ?VAR71 1.0) (<= 0.0 ?VAR72) (<= ?VAR72 1.0) (<= 0.0 ?VAR74) (<= ?VAR74 1.0) (<= 0.0 ?VAR73) (<= ?VAR73 1.0) (<= 0.0 ?VAR78) (<= ?VAR78 1.0) (<= 0.0 ?VAR77) (<= ?VAR77 1.0) (<= 0.0 ?VAR75) (<= ?VAR75 1.0) (<= 0.0 ?VAR76) (<= ?VAR76 1.0) (<= 0.0 ?VAR79) (<= ?VAR79 1.0) (<= 0.0 ?VAR80) (<= ?VAR80 1.0) (<= 0.0 ?VAR81) (<= ?VAR81 1.0) (<= 0.0 ?VAR82) (<= ?VAR82 1.0) (<= 0.0 ?VAR83) (<= ?VAR83 1.0) (<= 0.0 ?VAR84) (<= ?VAR84 1.0)))))
)

(benchmark plural19
; [0 < 1, 0 < VAR63, 1 == VAR63 + VAR64, 1 == 0 + VAR65, VAR66 == VAR63 + VAR64, VAR63 + VAR64 <= 1, VAR67 == 0 + VAR65, 0 + VAR65 <= 1, 0 < VAR68, VAR66 == VAR68 + VAR69, VAR67 == 1 + VAR70, VAR71 == VAR68 + VAR69, VAR68 + VAR69 <= 1, VAR72 == 1 + VAR70, 1 + VAR70 <= 1, 0 < VAR74, VAR74 <= VAR73, 1 == VAR72, VAR78 <= VAR77, 1 == VAR75 + VAR77, 1 == VAR76 + VAR78, VAR76 <= VAR75, VAR75 == VAR73 + VAR79, VAR76 == VAR74 + VAR80, 1 == 1 + VAR81, VAR80 <= VAR79, VAR82 == VAR73 + VAR79, VAR73 + VAR79 <= 1, VAR83 == VAR74 + VAR80, VAR74 + VAR80 <= 1, VAR84 == 1 + VAR81, 1 + VAR81 <= 1, VAR83 <= VAR82, 1 == VAR78 + VAR83]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR63 Real) (?VAR64 Real) (?VAR65 Real) (?VAR66 Real) (?VAR67 Real) (?VAR68 Real) (?VAR69 Real) (?VAR70 Real) (?VAR71 Real) (?VAR72 Real) (?VAR74 Real) (?VAR73 Real) (?VAR78 Real) (?VAR77 Real) (?VAR75 Real) (?VAR76 Real) (?VAR79 Real) (?VAR80 Real) (?VAR81 Real) (?VAR82 Real) (?VAR83 Real) (?VAR84 Real) (and (< 0.0 ?VAR63) (= 1.0 (+ ?VAR63 ?VAR64)) (= 1.0 (+ 0.0 ?VAR65)) (= ?VAR66 (+ ?VAR63 ?VAR64)) (<= (+ ?VAR63 ?VAR64) 1.0) (= ?VAR67 (+ 0.0 ?VAR65)) (<= (+ 0.0 ?VAR65) 1.0) (< 0.0 ?VAR68) (= ?VAR66 (+ ?VAR68 ?VAR69)) (= ?VAR67 (+ 1.0 ?VAR70)) (= ?VAR71 (+ ?VAR68 ?VAR69)) (<= (+ ?VAR68 ?VAR69) 1.0) (= ?VAR72 (+ 1.0 ?VAR70)) (<= (+ 1.0 ?VAR70) 1.0) (< 0.0 ?VAR74) (<= ?VAR74 ?VAR73) (= 1.0 ?VAR72) (<= ?VAR78 ?VAR77) (= 1.0 (+ ?VAR75 ?VAR77)) (= 1.0 (+ ?VAR76 ?VAR78)) (<= ?VAR76 ?VAR75) (= ?VAR75 (+ ?VAR73 ?VAR79)) (= ?VAR76 (+ ?VAR74 ?VAR80)) (= 1.0 (+ 1.0 ?VAR81)) (<= ?VAR80 ?VAR79) (= ?VAR82 (+ ?VAR73 ?VAR79)) (<= (+ ?VAR73 ?VAR79) 1.0) (= ?VAR83 (+ ?VAR74 ?VAR80)) (<= (+ ?VAR74 ?VAR80) 1.0) (= ?VAR84 (+ 1.0 ?VAR81)) (<= (+ 1.0 ?VAR81) 1.0) (<= ?VAR83 ?VAR82) (= 1.0 (+ ?VAR78 ?VAR83)) (<= 0.0 ?VAR63) (<= ?VAR63 1.0) (<= 0.0 ?VAR64) (<= ?VAR64 1.0) (<= 0.0 ?VAR65) (<= ?VAR65 1.0) (<= 0.0 ?VAR66) (<= ?VAR66 1.0) (<= 0.0 ?VAR67) (<= ?VAR67 1.0) (<= 0.0 ?VAR68) (<= ?VAR68 1.0) (<= 0.0 ?VAR69) (<= ?VAR69 1.0) (<= 0.0 ?VAR70) (<= ?VAR70 1.0) (<= 0.0 ?VAR71) (<= ?VAR71 1.0) (<= 0.0 ?VAR72) (<= ?VAR72 1.0) (<= 0.0 ?VAR74) (<= ?VAR74 1.0) (<= 0.0 ?VAR73) (<= ?VAR73 1.0) (<= 0.0 ?VAR78) (<= ?VAR78 1.0) (<= 0.0 ?VAR77) (<= ?VAR77 1.0) (<= 0.0 ?VAR75) (<= ?VAR75 1.0) (<= 0.0 ?VAR76) (<= ?VAR76 1.0) (<= 0.0 ?VAR79) (<= ?VAR79 1.0) (<= 0.0 ?VAR80) (<= ?VAR80 1.0) (<= 0.0 ?VAR81) (<= ?VAR81 1.0) (<= 0.0 ?VAR82) (<= ?VAR82 1.0) (<= 0.0 ?VAR83) (<= ?VAR83 1.0) (<= 0.0 ?VAR84) (<= ?VAR84 1.0)))))
)

(benchmark plural20
; [0 < 1, 0 < VAR63, 1 == VAR63 + VAR64, 1 == 0 + VAR65, VAR66 == VAR63 + VAR64, VAR63 + VAR64 <= 1, VAR67 == 0 + VAR65, 0 + VAR65 <= 1, 0 < VAR68, VAR66 == VAR68 + VAR69, VAR67 == 1 + VAR70, VAR71 == VAR68 + VAR69, VAR68 + VAR69 <= 1, VAR72 == 1 + VAR70, 1 + VAR70 <= 1, 0 < VAR74, VAR74 <= VAR73, 1 == VAR72, VAR78 <= VAR77, 1 == VAR75 + VAR77, 1 == VAR76 + VAR78, VAR76 <= VAR75, VAR75 == VAR73 + VAR79, VAR76 == VAR74 + VAR80, 1 == 1 + VAR81, VAR80 <= VAR79, VAR82 == VAR73 + VAR79, VAR73 + VAR79 <= 1, VAR83 == VAR74 + VAR80, VAR74 + VAR80 <= 1, VAR84 == 1 + VAR81, 1 + VAR81 <= 1, VAR83 <= VAR82]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR63 Real) (?VAR64 Real) (?VAR65 Real) (?VAR66 Real) (?VAR67 Real) (?VAR68 Real) (?VAR69 Real) (?VAR70 Real) (?VAR71 Real) (?VAR72 Real) (?VAR74 Real) (?VAR73 Real) (?VAR78 Real) (?VAR77 Real) (?VAR75 Real) (?VAR76 Real) (?VAR79 Real) (?VAR80 Real) (?VAR81 Real) (?VAR82 Real) (?VAR83 Real) (?VAR84 Real) (and (< 0.0 ?VAR63) (= 1.0 (+ ?VAR63 ?VAR64)) (= 1.0 (+ 0.0 ?VAR65)) (= ?VAR66 (+ ?VAR63 ?VAR64)) (<= (+ ?VAR63 ?VAR64) 1.0) (= ?VAR67 (+ 0.0 ?VAR65)) (<= (+ 0.0 ?VAR65) 1.0) (< 0.0 ?VAR68) (= ?VAR66 (+ ?VAR68 ?VAR69)) (= ?VAR67 (+ 1.0 ?VAR70)) (= ?VAR71 (+ ?VAR68 ?VAR69)) (<= (+ ?VAR68 ?VAR69) 1.0) (= ?VAR72 (+ 1.0 ?VAR70)) (<= (+ 1.0 ?VAR70) 1.0) (< 0.0 ?VAR74) (<= ?VAR74 ?VAR73) (= 1.0 ?VAR72) (<= ?VAR78 ?VAR77) (= 1.0 (+ ?VAR75 ?VAR77)) (= 1.0 (+ ?VAR76 ?VAR78)) (<= ?VAR76 ?VAR75) (= ?VAR75 (+ ?VAR73 ?VAR79)) (= ?VAR76 (+ ?VAR74 ?VAR80)) (= 1.0 (+ 1.0 ?VAR81)) (<= ?VAR80 ?VAR79) (= ?VAR82 (+ ?VAR73 ?VAR79)) (<= (+ ?VAR73 ?VAR79) 1.0) (= ?VAR83 (+ ?VAR74 ?VAR80)) (<= (+ ?VAR74 ?VAR80) 1.0) (= ?VAR84 (+ 1.0 ?VAR81)) (<= (+ 1.0 ?VAR81) 1.0) (<= ?VAR83 ?VAR82) (<= 0.0 ?VAR63) (<= ?VAR63 1.0) (<= 0.0 ?VAR64) (<= ?VAR64 1.0) (<= 0.0 ?VAR65) (<= ?VAR65 1.0) (<= 0.0 ?VAR66) (<= ?VAR66 1.0) (<= 0.0 ?VAR67) (<= ?VAR67 1.0) (<= 0.0 ?VAR68) (<= ?VAR68 1.0) (<= 0.0 ?VAR69) (<= ?VAR69 1.0) (<= 0.0 ?VAR70) (<= ?VAR70 1.0) (<= 0.0 ?VAR71) (<= ?VAR71 1.0) (<= 0.0 ?VAR72) (<= ?VAR72 1.0) (<= 0.0 ?VAR74) (<= ?VAR74 1.0) (<= 0.0 ?VAR73) (<= ?VAR73 1.0) (<= 0.0 ?VAR78) (<= ?VAR78 1.0) (<= 0.0 ?VAR77) (<= ?VAR77 1.0) (<= 0.0 ?VAR75) (<= ?VAR75 1.0) (<= 0.0 ?VAR76) (<= ?VAR76 1.0) (<= 0.0 ?VAR79) (<= ?VAR79 1.0) (<= 0.0 ?VAR80) (<= ?VAR80 1.0) (<= 0.0 ?VAR81) (<= ?VAR81 1.0) (<= 0.0 ?VAR82) (<= ?VAR82 1.0) (<= 0.0 ?VAR83) (<= ?VAR83 1.0) (<= 0.0 ?VAR84) (<= ?VAR84 1.0)))))
)

(benchmark plural21
; [0 < 1, 0 < VAR63, 1 == VAR63 + VAR64, 1 == 0 + VAR65, VAR66 == VAR63 + VAR64, VAR63 + VAR64 <= 1, VAR67 == 0 + VAR65, 0 + VAR65 <= 1, 0 < VAR68, VAR66 == VAR68 + VAR69, VAR67 == 1 + VAR70, VAR71 == VAR68 + VAR69, VAR68 + VAR69 <= 1, VAR72 == 1 + VAR70, 1 + VAR70 <= 1, 0 < VAR74, VAR74 <= VAR73, 1 == VAR72, VAR78 <= VAR77, 1 == VAR75 + VAR77, 1 == VAR76 + VAR78, VAR76 <= VAR75, VAR75 == VAR73 + VAR79, VAR76 == VAR74 + VAR80, 1 == 1 + VAR81, VAR80 <= VAR79, VAR82 == VAR73 + VAR79, VAR73 + VAR79 <= 1, VAR83 == VAR74 + VAR80, VAR74 + VAR80 <= 1, VAR84 == 1 + VAR81, 1 + VAR81 <= 1, VAR83 <= VAR82, 1 == VAR78 + VAR83]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR63 Real) (?VAR64 Real) (?VAR65 Real) (?VAR66 Real) (?VAR67 Real) (?VAR68 Real) (?VAR69 Real) (?VAR70 Real) (?VAR71 Real) (?VAR72 Real) (?VAR74 Real) (?VAR73 Real) (?VAR78 Real) (?VAR77 Real) (?VAR75 Real) (?VAR76 Real) (?VAR79 Real) (?VAR80 Real) (?VAR81 Real) (?VAR82 Real) (?VAR83 Real) (?VAR84 Real) (and (< 0.0 ?VAR63) (= 1.0 (+ ?VAR63 ?VAR64)) (= 1.0 (+ 0.0 ?VAR65)) (= ?VAR66 (+ ?VAR63 ?VAR64)) (<= (+ ?VAR63 ?VAR64) 1.0) (= ?VAR67 (+ 0.0 ?VAR65)) (<= (+ 0.0 ?VAR65) 1.0) (< 0.0 ?VAR68) (= ?VAR66 (+ ?VAR68 ?VAR69)) (= ?VAR67 (+ 1.0 ?VAR70)) (= ?VAR71 (+ ?VAR68 ?VAR69)) (<= (+ ?VAR68 ?VAR69) 1.0) (= ?VAR72 (+ 1.0 ?VAR70)) (<= (+ 1.0 ?VAR70) 1.0) (< 0.0 ?VAR74) (<= ?VAR74 ?VAR73) (= 1.0 ?VAR72) (<= ?VAR78 ?VAR77) (= 1.0 (+ ?VAR75 ?VAR77)) (= 1.0 (+ ?VAR76 ?VAR78)) (<= ?VAR76 ?VAR75) (= ?VAR75 (+ ?VAR73 ?VAR79)) (= ?VAR76 (+ ?VAR74 ?VAR80)) (= 1.0 (+ 1.0 ?VAR81)) (<= ?VAR80 ?VAR79) (= ?VAR82 (+ ?VAR73 ?VAR79)) (<= (+ ?VAR73 ?VAR79) 1.0) (= ?VAR83 (+ ?VAR74 ?VAR80)) (<= (+ ?VAR74 ?VAR80) 1.0) (= ?VAR84 (+ 1.0 ?VAR81)) (<= (+ 1.0 ?VAR81) 1.0) (<= ?VAR83 ?VAR82) (= 1.0 (+ ?VAR78 ?VAR83)) (<= 0.0 ?VAR63) (<= ?VAR63 1.0) (<= 0.0 ?VAR64) (<= ?VAR64 1.0) (<= 0.0 ?VAR65) (<= ?VAR65 1.0) (<= 0.0 ?VAR66) (<= ?VAR66 1.0) (<= 0.0 ?VAR67) (<= ?VAR67 1.0) (<= 0.0 ?VAR68) (<= ?VAR68 1.0) (<= 0.0 ?VAR69) (<= ?VAR69 1.0) (<= 0.0 ?VAR70) (<= ?VAR70 1.0) (<= 0.0 ?VAR71) (<= ?VAR71 1.0) (<= 0.0 ?VAR72) (<= ?VAR72 1.0) (<= 0.0 ?VAR74) (<= ?VAR74 1.0) (<= 0.0 ?VAR73) (<= ?VAR73 1.0) (<= 0.0 ?VAR78) (<= ?VAR78 1.0) (<= 0.0 ?VAR77) (<= ?VAR77 1.0) (<= 0.0 ?VAR75) (<= ?VAR75 1.0) (<= 0.0 ?VAR76) (<= ?VAR76 1.0) (<= 0.0 ?VAR79) (<= ?VAR79 1.0) (<= 0.0 ?VAR80) (<= ?VAR80 1.0) (<= 0.0 ?VAR81) (<= ?VAR81 1.0) (<= 0.0 ?VAR82) (<= ?VAR82 1.0) (<= 0.0 ?VAR83) (<= ?VAR83 1.0) (<= 0.0 ?VAR84) (<= ?VAR84 1.0)))))
)

(benchmark plural22
; [0 < 1, 0 < VAR60, 1 == VAR60 + VAR61, 1 == 1 + VAR62]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR60 Real) (?VAR61 Real) (?VAR62 Real) (and (< 0.0 ?VAR60) (= 1.0 (+ ?VAR60 ?VAR61)) (= 1.0 (+ 1.0 ?VAR62)) (<= 0.0 ?VAR60) (<= ?VAR60 1.0) (<= 0.0 ?VAR61) (<= ?VAR61 1.0) (<= 0.0 ?VAR62) (<= ?VAR62 1.0)))))
)

(benchmark plural23
; [0 < 1, 0 < VAR63, 1 == VAR63 + VAR64, 1 == 0 + VAR65, VAR66 == VAR63 + VAR64, VAR63 + VAR64 <= 1, VAR67 == 0 + VAR65, 0 + VAR65 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR63 Real) (?VAR64 Real) (?VAR65 Real) (?VAR66 Real) (?VAR67 Real) (and (< 0.0 ?VAR63) (= 1.0 (+ ?VAR63 ?VAR64)) (= 1.0 (+ 0.0 ?VAR65)) (= ?VAR66 (+ ?VAR63 ?VAR64)) (<= (+ ?VAR63 ?VAR64) 1.0) (= ?VAR67 (+ 0.0 ?VAR65)) (<= (+ 0.0 ?VAR65) 1.0) (<= 0.0 ?VAR63) (<= ?VAR63 1.0) (<= 0.0 ?VAR64) (<= ?VAR64 1.0) (<= 0.0 ?VAR65) (<= ?VAR65 1.0) (<= 0.0 ?VAR66) (<= ?VAR66 1.0) (<= 0.0 ?VAR67) (<= ?VAR67 1.0)))))
)

(benchmark plural24
; [0 < 1, 0 < VAR63, 1 == VAR63 + VAR64, 1 == 0 + VAR65, VAR66 == VAR63 + VAR64, VAR63 + VAR64 <= 1, VAR67 == 0 + VAR65, 0 + VAR65 <= 1, 0 < VAR68, VAR66 == VAR68 + VAR69, VAR67 == 1 + VAR70, VAR71 == VAR68 + VAR69, VAR68 + VAR69 <= 1, VAR72 == 1 + VAR70, 1 + VAR70 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR63 Real) (?VAR64 Real) (?VAR65 Real) (?VAR66 Real) (?VAR67 Real) (?VAR68 Real) (?VAR69 Real) (?VAR70 Real) (?VAR71 Real) (?VAR72 Real) (and (< 0.0 ?VAR63) (= 1.0 (+ ?VAR63 ?VAR64)) (= 1.0 (+ 0.0 ?VAR65)) (= ?VAR66 (+ ?VAR63 ?VAR64)) (<= (+ ?VAR63 ?VAR64) 1.0) (= ?VAR67 (+ 0.0 ?VAR65)) (<= (+ 0.0 ?VAR65) 1.0) (< 0.0 ?VAR68) (= ?VAR66 (+ ?VAR68 ?VAR69)) (= ?VAR67 (+ 1.0 ?VAR70)) (= ?VAR71 (+ ?VAR68 ?VAR69)) (<= (+ ?VAR68 ?VAR69) 1.0) (= ?VAR72 (+ 1.0 ?VAR70)) (<= (+ 1.0 ?VAR70) 1.0) (<= 0.0 ?VAR63) (<= ?VAR63 1.0) (<= 0.0 ?VAR64) (<= ?VAR64 1.0) (<= 0.0 ?VAR65) (<= ?VAR65 1.0) (<= 0.0 ?VAR66) (<= ?VAR66 1.0) (<= 0.0 ?VAR67) (<= ?VAR67 1.0) (<= 0.0 ?VAR68) (<= ?VAR68 1.0) (<= 0.0 ?VAR69) (<= ?VAR69 1.0) (<= 0.0 ?VAR70) (<= ?VAR70 1.0) (<= 0.0 ?VAR71) (<= ?VAR71 1.0) (<= 0.0 ?VAR72) (<= ?VAR72 1.0)))))
)

(benchmark plural25
; []
  :status unsat
  :assumption true
  :formula (not (implies true true))
)

(benchmark plural26
; [0 < 1, 0 < VAR63, 1 == VAR63 + VAR64, 1 == 0 + VAR65, VAR66 == VAR63 + VAR64, VAR63 + VAR64 <= 1, VAR67 == 0 + VAR65, 0 + VAR65 <= 1, 0 < VAR68, VAR66 == VAR68 + VAR69, VAR67 == 1 + VAR70, VAR71 == VAR68 + VAR69, VAR68 + VAR69 <= 1, VAR72 == 1 + VAR70, 1 + VAR70 <= 1, 0 < VAR74, VAR74 <= VAR73, 1 == VAR72, VAR78 <= VAR77, 1 == VAR75 + VAR77, 1 == VAR76 + VAR78, VAR76 <= VAR75, VAR75 == VAR73 + VAR79, VAR76 == VAR74 + VAR80, 1 == 1 + VAR81, VAR80 <= VAR79, VAR82 == VAR73 + VAR79, VAR73 + VAR79 <= 1, VAR83 == VAR74 + VAR80, VAR74 + VAR80 <= 1, VAR84 == 1 + VAR81, 1 + VAR81 <= 1, VAR83 <= VAR82]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR63 Real) (?VAR64 Real) (?VAR65 Real) (?VAR66 Real) (?VAR67 Real) (?VAR68 Real) (?VAR69 Real) (?VAR70 Real) (?VAR71 Real) (?VAR72 Real) (?VAR74 Real) (?VAR73 Real) (?VAR78 Real) (?VAR77 Real) (?VAR75 Real) (?VAR76 Real) (?VAR79 Real) (?VAR80 Real) (?VAR81 Real) (?VAR82 Real) (?VAR83 Real) (?VAR84 Real) (and (< 0.0 ?VAR63) (= 1.0 (+ ?VAR63 ?VAR64)) (= 1.0 (+ 0.0 ?VAR65)) (= ?VAR66 (+ ?VAR63 ?VAR64)) (<= (+ ?VAR63 ?VAR64) 1.0) (= ?VAR67 (+ 0.0 ?VAR65)) (<= (+ 0.0 ?VAR65) 1.0) (< 0.0 ?VAR68) (= ?VAR66 (+ ?VAR68 ?VAR69)) (= ?VAR67 (+ 1.0 ?VAR70)) (= ?VAR71 (+ ?VAR68 ?VAR69)) (<= (+ ?VAR68 ?VAR69) 1.0) (= ?VAR72 (+ 1.0 ?VAR70)) (<= (+ 1.0 ?VAR70) 1.0) (< 0.0 ?VAR74) (<= ?VAR74 ?VAR73) (= 1.0 ?VAR72) (<= ?VAR78 ?VAR77) (= 1.0 (+ ?VAR75 ?VAR77)) (= 1.0 (+ ?VAR76 ?VAR78)) (<= ?VAR76 ?VAR75) (= ?VAR75 (+ ?VAR73 ?VAR79)) (= ?VAR76 (+ ?VAR74 ?VAR80)) (= 1.0 (+ 1.0 ?VAR81)) (<= ?VAR80 ?VAR79) (= ?VAR82 (+ ?VAR73 ?VAR79)) (<= (+ ?VAR73 ?VAR79) 1.0) (= ?VAR83 (+ ?VAR74 ?VAR80)) (<= (+ ?VAR74 ?VAR80) 1.0) (= ?VAR84 (+ 1.0 ?VAR81)) (<= (+ 1.0 ?VAR81) 1.0) (<= ?VAR83 ?VAR82) (<= 0.0 ?VAR63) (<= ?VAR63 1.0) (<= 0.0 ?VAR64) (<= ?VAR64 1.0) (<= 0.0 ?VAR65) (<= ?VAR65 1.0) (<= 0.0 ?VAR66) (<= ?VAR66 1.0) (<= 0.0 ?VAR67) (<= ?VAR67 1.0) (<= 0.0 ?VAR68) (<= ?VAR68 1.0) (<= 0.0 ?VAR69) (<= ?VAR69 1.0) (<= 0.0 ?VAR70) (<= ?VAR70 1.0) (<= 0.0 ?VAR71) (<= ?VAR71 1.0) (<= 0.0 ?VAR72) (<= ?VAR72 1.0) (<= 0.0 ?VAR74) (<= ?VAR74 1.0) (<= 0.0 ?VAR73) (<= ?VAR73 1.0) (<= 0.0 ?VAR78) (<= ?VAR78 1.0) (<= 0.0 ?VAR77) (<= ?VAR77 1.0) (<= 0.0 ?VAR75) (<= ?VAR75 1.0) (<= 0.0 ?VAR76) (<= ?VAR76 1.0) (<= 0.0 ?VAR79) (<= ?VAR79 1.0) (<= 0.0 ?VAR80) (<= ?VAR80 1.0) (<= 0.0 ?VAR81) (<= ?VAR81 1.0) (<= 0.0 ?VAR82) (<= ?VAR82 1.0) (<= 0.0 ?VAR83) (<= ?VAR83 1.0) (<= 0.0 ?VAR84) (<= ?VAR84 1.0)))))
)

; Results for doubleRemove
(benchmark plural27
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0)))))
)

(benchmark plural28
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107, 1 == VAR103 + VAR108]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (= 1.0 (+ ?VAR103 ?VAR108)) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0)))))
)

(benchmark plural29
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0)))))
)

(benchmark plural30
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107, 1 == VAR103 + VAR108]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (= 1.0 (+ ?VAR103 ?VAR108)) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0)))))
)

(benchmark plural31
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107, 1 == VAR103 + VAR108, 0 < VAR111, VAR111 <= VAR110, VAR115 <= VAR114, 1 == VAR112 + VAR114, 1 == VAR113 + VAR115, VAR113 <= VAR112, VAR112 == VAR110 + VAR116, VAR113 == VAR111 + VAR117, 1 == 1 + VAR118, VAR117 <= VAR116, VAR119 == VAR110 + VAR116, VAR110 + VAR116 <= 1, VAR120 == VAR111 + VAR117, VAR111 + VAR117 <= 1, VAR121 == 1 + VAR118, 1 + VAR118 <= 1, VAR120 <= VAR119]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (?VAR111 Real) (?VAR110 Real) (?VAR115 Real) (?VAR114 Real) (?VAR112 Real) (?VAR113 Real) (?VAR116 Real) (?VAR117 Real) (?VAR118 Real) (?VAR119 Real) (?VAR120 Real) (?VAR121 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (= 1.0 (+ ?VAR103 ?VAR108)) (< 0.0 ?VAR111) (<= ?VAR111 ?VAR110) (<= ?VAR115 ?VAR114) (= 1.0 (+ ?VAR112 ?VAR114)) (= 1.0 (+ ?VAR113 ?VAR115)) (<= ?VAR113 ?VAR112) (= ?VAR112 (+ ?VAR110 ?VAR116)) (= ?VAR113 (+ ?VAR111 ?VAR117)) (= 1.0 (+ 1.0 ?VAR118)) (<= ?VAR117 ?VAR116) (= ?VAR119 (+ ?VAR110 ?VAR116)) (<= (+ ?VAR110 ?VAR116) 1.0) (= ?VAR120 (+ ?VAR111 ?VAR117)) (<= (+ ?VAR111 ?VAR117) 1.0) (= ?VAR121 (+ 1.0 ?VAR118)) (<= (+ 1.0 ?VAR118) 1.0) (<= ?VAR120 ?VAR119) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0) (<= 0.0 ?VAR111) (<= ?VAR111 1.0) (<= 0.0 ?VAR110) (<= ?VAR110 1.0) (<= 0.0 ?VAR115) (<= ?VAR115 1.0) (<= 0.0 ?VAR114) (<= ?VAR114 1.0) (<= 0.0 ?VAR112) (<= ?VAR112 1.0) (<= 0.0 ?VAR113) (<= ?VAR113 1.0) (<= 0.0 ?VAR116) (<= ?VAR116 1.0) (<= 0.0 ?VAR117) (<= ?VAR117 1.0) (<= 0.0 ?VAR118) (<= ?VAR118 1.0) (<= 0.0 ?VAR119) (<= ?VAR119 1.0) (<= 0.0 ?VAR120) (<= ?VAR120 1.0) (<= 0.0 ?VAR121) (<= ?VAR121 1.0)))))
)

(benchmark plural32
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107, 1 == VAR103 + VAR108, 0 < VAR111, VAR111 <= VAR110, VAR115 <= VAR114, 1 == VAR112 + VAR114, 1 == VAR113 + VAR115, VAR113 <= VAR112, VAR112 == VAR110 + VAR116, VAR113 == VAR111 + VAR117, 1 == 1 + VAR118, VAR117 <= VAR116, VAR119 == VAR110 + VAR116, VAR110 + VAR116 <= 1, VAR120 == VAR111 + VAR117, VAR111 + VAR117 <= 1, VAR121 == 1 + VAR118, 1 + VAR118 <= 1, VAR120 <= VAR119, 1 == VAR115 + VAR120]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (?VAR111 Real) (?VAR110 Real) (?VAR115 Real) (?VAR114 Real) (?VAR112 Real) (?VAR113 Real) (?VAR116 Real) (?VAR117 Real) (?VAR118 Real) (?VAR119 Real) (?VAR120 Real) (?VAR121 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (= 1.0 (+ ?VAR103 ?VAR108)) (< 0.0 ?VAR111) (<= ?VAR111 ?VAR110) (<= ?VAR115 ?VAR114) (= 1.0 (+ ?VAR112 ?VAR114)) (= 1.0 (+ ?VAR113 ?VAR115)) (<= ?VAR113 ?VAR112) (= ?VAR112 (+ ?VAR110 ?VAR116)) (= ?VAR113 (+ ?VAR111 ?VAR117)) (= 1.0 (+ 1.0 ?VAR118)) (<= ?VAR117 ?VAR116) (= ?VAR119 (+ ?VAR110 ?VAR116)) (<= (+ ?VAR110 ?VAR116) 1.0) (= ?VAR120 (+ ?VAR111 ?VAR117)) (<= (+ ?VAR111 ?VAR117) 1.0) (= ?VAR121 (+ 1.0 ?VAR118)) (<= (+ 1.0 ?VAR118) 1.0) (<= ?VAR120 ?VAR119) (= 1.0 (+ ?VAR115 ?VAR120)) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0) (<= 0.0 ?VAR111) (<= ?VAR111 1.0) (<= 0.0 ?VAR110) (<= ?VAR110 1.0) (<= 0.0 ?VAR115) (<= ?VAR115 1.0) (<= 0.0 ?VAR114) (<= ?VAR114 1.0) (<= 0.0 ?VAR112) (<= ?VAR112 1.0) (<= 0.0 ?VAR113) (<= ?VAR113 1.0) (<= 0.0 ?VAR116) (<= ?VAR116 1.0) (<= 0.0 ?VAR117) (<= ?VAR117 1.0) (<= 0.0 ?VAR118) (<= ?VAR118 1.0) (<= 0.0 ?VAR119) (<= ?VAR119 1.0) (<= 0.0 ?VAR120) (<= ?VAR120 1.0) (<= 0.0 ?VAR121) (<= ?VAR121 1.0)))))
)

(benchmark plural33
; [0 < 1, 0 < VAR85, 1 == VAR85 + VAR86, 1 == 1 + VAR87]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR85 Real) (?VAR86 Real) (?VAR87 Real) (and (< 0.0 ?VAR85) (= 1.0 (+ ?VAR85 ?VAR86)) (= 1.0 (+ 1.0 ?VAR87)) (<= 0.0 ?VAR85) (<= ?VAR85 1.0) (<= 0.0 ?VAR86) (<= ?VAR86 1.0) (<= 0.0 ?VAR87) (<= ?VAR87 1.0)))))
)

(benchmark plural34
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0)))))
)

(benchmark plural35
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0)))))
)

(benchmark plural36
; []
  :status unsat
  :assumption true
  :formula (not (implies true true))
)

(benchmark plural37
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0)))))
)

(benchmark plural38
; [0 < 1, 0 < VAR88, 1 == VAR88 + VAR89, 1 == 0 + VAR90, VAR91 == VAR88 + VAR89, VAR88 + VAR89 <= 1, VAR92 == 0 + VAR90, 0 + VAR90 <= 1, 0 < VAR93, VAR91 == VAR93 + VAR94, VAR92 == 1 + VAR95, VAR96 == VAR93 + VAR94, VAR93 + VAR94 <= 1, VAR97 == 1 + VAR95, 1 + VAR95 <= 1, 0 < VAR99, VAR99 <= VAR98, 1 == VAR97, VAR103 <= VAR102, 1 == VAR100 + VAR102, 1 == VAR101 + VAR103, VAR101 <= VAR100, VAR100 == VAR104 + VAR98, VAR101 == VAR105 + VAR99, 1 == 1 + VAR106, VAR105 <= VAR104, VAR107 == VAR104 + VAR98, VAR104 + VAR98 <= 1, VAR108 == VAR105 + VAR99, VAR105 + VAR99 <= 1, VAR109 == 1 + VAR106, 1 + VAR106 <= 1, VAR108 <= VAR107, 1 == VAR103 + VAR108, 0 < VAR111, VAR111 <= VAR110, VAR115 <= VAR114, 1 == VAR112 + VAR114, 1 == VAR113 + VAR115, VAR113 <= VAR112, VAR112 == VAR110 + VAR116, VAR113 == VAR111 + VAR117, 1 == 1 + VAR118, VAR117 <= VAR116, VAR119 == VAR110 + VAR116, VAR110 + VAR116 <= 1, VAR120 == VAR111 + VAR117, VAR111 + VAR117 <= 1, VAR121 == 1 + VAR118, 1 + VAR118 <= 1, VAR120 <= VAR119]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR88 Real) (?VAR89 Real) (?VAR90 Real) (?VAR91 Real) (?VAR92 Real) (?VAR93 Real) (?VAR94 Real) (?VAR95 Real) (?VAR96 Real) (?VAR97 Real) (?VAR99 Real) (?VAR98 Real) (?VAR103 Real) (?VAR102 Real) (?VAR100 Real) (?VAR101 Real) (?VAR104 Real) (?VAR105 Real) (?VAR106 Real) (?VAR107 Real) (?VAR108 Real) (?VAR109 Real) (?VAR111 Real) (?VAR110 Real) (?VAR115 Real) (?VAR114 Real) (?VAR112 Real) (?VAR113 Real) (?VAR116 Real) (?VAR117 Real) (?VAR118 Real) (?VAR119 Real) (?VAR120 Real) (?VAR121 Real) (and (< 0.0 ?VAR88) (= 1.0 (+ ?VAR88 ?VAR89)) (= 1.0 (+ 0.0 ?VAR90)) (= ?VAR91 (+ ?VAR88 ?VAR89)) (<= (+ ?VAR88 ?VAR89) 1.0) (= ?VAR92 (+ 0.0 ?VAR90)) (<= (+ 0.0 ?VAR90) 1.0) (< 0.0 ?VAR93) (= ?VAR91 (+ ?VAR93 ?VAR94)) (= ?VAR92 (+ 1.0 ?VAR95)) (= ?VAR96 (+ ?VAR93 ?VAR94)) (<= (+ ?VAR93 ?VAR94) 1.0) (= ?VAR97 (+ 1.0 ?VAR95)) (<= (+ 1.0 ?VAR95) 1.0) (< 0.0 ?VAR99) (<= ?VAR99 ?VAR98) (= 1.0 ?VAR97) (<= ?VAR103 ?VAR102) (= 1.0 (+ ?VAR100 ?VAR102)) (= 1.0 (+ ?VAR101 ?VAR103)) (<= ?VAR101 ?VAR100) (= ?VAR100 (+ ?VAR104 ?VAR98)) (= ?VAR101 (+ ?VAR105 ?VAR99)) (= 1.0 (+ 1.0 ?VAR106)) (<= ?VAR105 ?VAR104) (= ?VAR107 (+ ?VAR104 ?VAR98)) (<= (+ ?VAR104 ?VAR98) 1.0) (= ?VAR108 (+ ?VAR105 ?VAR99)) (<= (+ ?VAR105 ?VAR99) 1.0) (= ?VAR109 (+ 1.0 ?VAR106)) (<= (+ 1.0 ?VAR106) 1.0) (<= ?VAR108 ?VAR107) (= 1.0 (+ ?VAR103 ?VAR108)) (< 0.0 ?VAR111) (<= ?VAR111 ?VAR110) (<= ?VAR115 ?VAR114) (= 1.0 (+ ?VAR112 ?VAR114)) (= 1.0 (+ ?VAR113 ?VAR115)) (<= ?VAR113 ?VAR112) (= ?VAR112 (+ ?VAR110 ?VAR116)) (= ?VAR113 (+ ?VAR111 ?VAR117)) (= 1.0 (+ 1.0 ?VAR118)) (<= ?VAR117 ?VAR116) (= ?VAR119 (+ ?VAR110 ?VAR116)) (<= (+ ?VAR110 ?VAR116) 1.0) (= ?VAR120 (+ ?VAR111 ?VAR117)) (<= (+ ?VAR111 ?VAR117) 1.0) (= ?VAR121 (+ 1.0 ?VAR118)) (<= (+ 1.0 ?VAR118) 1.0) (<= ?VAR120 ?VAR119) (<= 0.0 ?VAR88) (<= ?VAR88 1.0) (<= 0.0 ?VAR89) (<= ?VAR89 1.0) (<= 0.0 ?VAR90) (<= ?VAR90 1.0) (<= 0.0 ?VAR91) (<= ?VAR91 1.0) (<= 0.0 ?VAR92) (<= ?VAR92 1.0) (<= 0.0 ?VAR93) (<= ?VAR93 1.0) (<= 0.0 ?VAR94) (<= ?VAR94 1.0) (<= 0.0 ?VAR95) (<= ?VAR95 1.0) (<= 0.0 ?VAR96) (<= ?VAR96 1.0) (<= 0.0 ?VAR97) (<= ?VAR97 1.0) (<= 0.0 ?VAR99) (<= ?VAR99 1.0) (<= 0.0 ?VAR98) (<= ?VAR98 1.0) (<= 0.0 ?VAR103) (<= ?VAR103 1.0) (<= 0.0 ?VAR102) (<= ?VAR102 1.0) (<= 0.0 ?VAR100) (<= ?VAR100 1.0) (<= 0.0 ?VAR101) (<= ?VAR101 1.0) (<= 0.0 ?VAR104) (<= ?VAR104 1.0) (<= 0.0 ?VAR105) (<= ?VAR105 1.0) (<= 0.0 ?VAR106) (<= ?VAR106 1.0) (<= 0.0 ?VAR107) (<= ?VAR107 1.0) (<= 0.0 ?VAR108) (<= ?VAR108 1.0) (<= 0.0 ?VAR109) (<= ?VAR109 1.0) (<= 0.0 ?VAR111) (<= ?VAR111 1.0) (<= 0.0 ?VAR110) (<= ?VAR110 1.0) (<= 0.0 ?VAR115) (<= ?VAR115 1.0) (<= 0.0 ?VAR114) (<= ?VAR114 1.0) (<= 0.0 ?VAR112) (<= ?VAR112 1.0) (<= 0.0 ?VAR113) (<= ?VAR113 1.0) (<= 0.0 ?VAR116) (<= ?VAR116 1.0) (<= 0.0 ?VAR117) (<= ?VAR117 1.0) (<= 0.0 ?VAR118) (<= ?VAR118 1.0) (<= 0.0 ?VAR119) (<= ?VAR119 1.0) (<= 0.0 ?VAR120) (<= ?VAR120 1.0) (<= 0.0 ?VAR121) (<= ?VAR121 1.0)))))
)

; Results for removeBeforeNext
(benchmark plural39
; [0 < 1, 0 < VAR122, 1 == VAR122 + VAR123, 1 == 0 + VAR124, VAR125 == VAR122 + VAR123, VAR122 + VAR123 <= 1, VAR126 == 0 + VAR124, 0 + VAR124 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR122 Real) (?VAR123 Real) (?VAR124 Real) (?VAR125 Real) (?VAR126 Real) (and (< 0.0 ?VAR122) (= 1.0 (+ ?VAR122 ?VAR123)) (= 1.0 (+ 0.0 ?VAR124)) (= ?VAR125 (+ ?VAR122 ?VAR123)) (<= (+ ?VAR122 ?VAR123) 1.0) (= ?VAR126 (+ 0.0 ?VAR124)) (<= (+ 0.0 ?VAR124) 1.0) (<= 0.0 ?VAR122) (<= ?VAR122 1.0) (<= 0.0 ?VAR123) (<= ?VAR123 1.0) (<= 0.0 ?VAR124) (<= ?VAR124 1.0) (<= 0.0 ?VAR125) (<= ?VAR125 1.0) (<= 0.0 ?VAR126) (<= ?VAR126 1.0)))))
)

(benchmark plural40
; [0 < 1, 0 < VAR122, 1 == VAR122 + VAR123, 1 == 0 + VAR124, VAR125 == VAR122 + VAR123, VAR122 + VAR123 <= 1, VAR126 == 0 + VAR124, 0 + VAR124 <= 1, 0 < VAR127, VAR125 == VAR127 + VAR128, VAR126 == 1 + VAR129, VAR130 == VAR127 + VAR128, VAR127 + VAR128 <= 1, VAR131 == 1 + VAR129, 1 + VAR129 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR122 Real) (?VAR123 Real) (?VAR124 Real) (?VAR125 Real) (?VAR126 Real) (?VAR127 Real) (?VAR128 Real) (?VAR129 Real) (?VAR130 Real) (?VAR131 Real) (and (< 0.0 ?VAR122) (= 1.0 (+ ?VAR122 ?VAR123)) (= 1.0 (+ 0.0 ?VAR124)) (= ?VAR125 (+ ?VAR122 ?VAR123)) (<= (+ ?VAR122 ?VAR123) 1.0) (= ?VAR126 (+ 0.0 ?VAR124)) (<= (+ 0.0 ?VAR124) 1.0) (< 0.0 ?VAR127) (= ?VAR125 (+ ?VAR127 ?VAR128)) (= ?VAR126 (+ 1.0 ?VAR129)) (= ?VAR130 (+ ?VAR127 ?VAR128)) (<= (+ ?VAR127 ?VAR128) 1.0) (= ?VAR131 (+ 1.0 ?VAR129)) (<= (+ 1.0 ?VAR129) 1.0) (<= 0.0 ?VAR122) (<= ?VAR122 1.0) (<= 0.0 ?VAR123) (<= ?VAR123 1.0) (<= 0.0 ?VAR124) (<= ?VAR124 1.0) (<= 0.0 ?VAR125) (<= ?VAR125 1.0) (<= 0.0 ?VAR126) (<= ?VAR126 1.0) (<= 0.0 ?VAR127) (<= ?VAR127 1.0) (<= 0.0 ?VAR128) (<= ?VAR128 1.0) (<= 0.0 ?VAR129) (<= ?VAR129 1.0) (<= 0.0 ?VAR130) (<= ?VAR130 1.0) (<= 0.0 ?VAR131) (<= ?VAR131 1.0)))))
)

(benchmark plural41
; [0 < 1, 0 < VAR122, 1 == VAR122 + VAR123, 1 == 0 + VAR124, VAR125 == VAR122 + VAR123, VAR122 + VAR123 <= 1, VAR126 == 0 + VAR124, 0 + VAR124 <= 1, 0 < VAR127, VAR125 == VAR127 + VAR128, VAR126 == 1 + VAR129, VAR130 == VAR127 + VAR128, VAR127 + VAR128 <= 1, VAR131 == 1 + VAR129, 1 + VAR129 <= 1, 0 < VAR132, VAR130 == VAR132 + VAR133, VAR131 == 0 + VAR134, VAR135 == VAR132 + VAR133, VAR132 + VAR133 <= 1, VAR136 == 0 + VAR134, 0 + VAR134 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR122 Real) (?VAR123 Real) (?VAR124 Real) (?VAR125 Real) (?VAR126 Real) (?VAR127 Real) (?VAR128 Real) (?VAR129 Real) (?VAR130 Real) (?VAR131 Real) (?VAR132 Real) (?VAR133 Real) (?VAR134 Real) (?VAR135 Real) (?VAR136 Real) (and (< 0.0 ?VAR122) (= 1.0 (+ ?VAR122 ?VAR123)) (= 1.0 (+ 0.0 ?VAR124)) (= ?VAR125 (+ ?VAR122 ?VAR123)) (<= (+ ?VAR122 ?VAR123) 1.0) (= ?VAR126 (+ 0.0 ?VAR124)) (<= (+ 0.0 ?VAR124) 1.0) (< 0.0 ?VAR127) (= ?VAR125 (+ ?VAR127 ?VAR128)) (= ?VAR126 (+ 1.0 ?VAR129)) (= ?VAR130 (+ ?VAR127 ?VAR128)) (<= (+ ?VAR127 ?VAR128) 1.0) (= ?VAR131 (+ 1.0 ?VAR129)) (<= (+ 1.0 ?VAR129) 1.0) (< 0.0 ?VAR132) (= ?VAR130 (+ ?VAR132 ?VAR133)) (= ?VAR131 (+ 0.0 ?VAR134)) (= ?VAR135 (+ ?VAR132 ?VAR133)) (<= (+ ?VAR132 ?VAR133) 1.0) (= ?VAR136 (+ 0.0 ?VAR134)) (<= (+ 0.0 ?VAR134) 1.0) (<= 0.0 ?VAR122) (<= ?VAR122 1.0) (<= 0.0 ?VAR123) (<= ?VAR123 1.0) (<= 0.0 ?VAR124) (<= ?VAR124 1.0) (<= 0.0 ?VAR125) (<= ?VAR125 1.0) (<= 0.0 ?VAR126) (<= ?VAR126 1.0) (<= 0.0 ?VAR127) (<= ?VAR127 1.0) (<= 0.0 ?VAR128) (<= ?VAR128 1.0) (<= 0.0 ?VAR129) (<= ?VAR129 1.0) (<= 0.0 ?VAR130) (<= ?VAR130 1.0) (<= 0.0 ?VAR131) (<= ?VAR131 1.0) (<= 0.0 ?VAR132) (<= ?VAR132 1.0) (<= 0.0 ?VAR133) (<= ?VAR133 1.0) (<= 0.0 ?VAR134) (<= ?VAR134 1.0) (<= 0.0 ?VAR135) (<= ?VAR135 1.0) (<= 0.0 ?VAR136) (<= ?VAR136 1.0)))))
)

(benchmark plural42
; [0 < 1, 0 < VAR122, 1 == VAR122 + VAR123, 1 == 0 + VAR124, VAR125 == VAR122 + VAR123, VAR122 + VAR123 <= 1, VAR126 == 0 + VAR124, 0 + VAR124 <= 1, 0 < VAR127, VAR125 == VAR127 + VAR128, VAR126 == 1 + VAR129, VAR130 == VAR127 + VAR128, VAR127 + VAR128 <= 1, VAR131 == 1 + VAR129, 1 + VAR129 <= 1, 0 < VAR132, VAR130 == VAR132 + VAR133, VAR131 == 0 + VAR134, VAR135 == VAR132 + VAR133, VAR132 + VAR133 <= 1, VAR136 == 0 + VAR134, 0 + VAR134 <= 1, 0 < VAR138, VAR138 <= VAR137, 1 == VAR136, VAR142 <= VAR141, 1 == VAR139 + VAR141, 1 == VAR140 + VAR142, VAR140 <= VAR139, VAR139 == VAR137 + VAR143, VAR140 == VAR138 + VAR144, 1 == 1 + VAR145, VAR144 <= VAR143, VAR146 == VAR137 + VAR143, VAR137 + VAR143 <= 1, VAR147 == VAR138 + VAR144, VAR138 + VAR144 <= 1, VAR148 == 1 + VAR145, 1 + VAR145 <= 1, VAR147 <= VAR146]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR122 Real) (?VAR123 Real) (?VAR124 Real) (?VAR125 Real) (?VAR126 Real) (?VAR127 Real) (?VAR128 Real) (?VAR129 Real) (?VAR130 Real) (?VAR131 Real) (?VAR132 Real) (?VAR133 Real) (?VAR134 Real) (?VAR135 Real) (?VAR136 Real) (?VAR138 Real) (?VAR137 Real) (?VAR142 Real) (?VAR141 Real) (?VAR139 Real) (?VAR140 Real) (?VAR143 Real) (?VAR144 Real) (?VAR145 Real) (?VAR146 Real) (?VAR147 Real) (?VAR148 Real) (and (< 0.0 ?VAR122) (= 1.0 (+ ?VAR122 ?VAR123)) (= 1.0 (+ 0.0 ?VAR124)) (= ?VAR125 (+ ?VAR122 ?VAR123)) (<= (+ ?VAR122 ?VAR123) 1.0) (= ?VAR126 (+ 0.0 ?VAR124)) (<= (+ 0.0 ?VAR124) 1.0) (< 0.0 ?VAR127) (= ?VAR125 (+ ?VAR127 ?VAR128)) (= ?VAR126 (+ 1.0 ?VAR129)) (= ?VAR130 (+ ?VAR127 ?VAR128)) (<= (+ ?VAR127 ?VAR128) 1.0) (= ?VAR131 (+ 1.0 ?VAR129)) (<= (+ 1.0 ?VAR129) 1.0) (< 0.0 ?VAR132) (= ?VAR130 (+ ?VAR132 ?VAR133)) (= ?VAR131 (+ 0.0 ?VAR134)) (= ?VAR135 (+ ?VAR132 ?VAR133)) (<= (+ ?VAR132 ?VAR133) 1.0) (= ?VAR136 (+ 0.0 ?VAR134)) (<= (+ 0.0 ?VAR134) 1.0) (< 0.0 ?VAR138) (<= ?VAR138 ?VAR137) (= 1.0 ?VAR136) (<= ?VAR142 ?VAR141) (= 1.0 (+ ?VAR139 ?VAR141)) (= 1.0 (+ ?VAR140 ?VAR142)) (<= ?VAR140 ?VAR139) (= ?VAR139 (+ ?VAR137 ?VAR143)) (= ?VAR140 (+ ?VAR138 ?VAR144)) (= 1.0 (+ 1.0 ?VAR145)) (<= ?VAR144 ?VAR143) (= ?VAR146 (+ ?VAR137 ?VAR143)) (<= (+ ?VAR137 ?VAR143) 1.0) (= ?VAR147 (+ ?VAR138 ?VAR144)) (<= (+ ?VAR138 ?VAR144) 1.0) (= ?VAR148 (+ 1.0 ?VAR145)) (<= (+ 1.0 ?VAR145) 1.0) (<= ?VAR147 ?VAR146) (<= 0.0 ?VAR122) (<= ?VAR122 1.0) (<= 0.0 ?VAR123) (<= ?VAR123 1.0) (<= 0.0 ?VAR124) (<= ?VAR124 1.0) (<= 0.0 ?VAR125) (<= ?VAR125 1.0) (<= 0.0 ?VAR126) (<= ?VAR126 1.0) (<= 0.0 ?VAR127) (<= ?VAR127 1.0) (<= 0.0 ?VAR128) (<= ?VAR128 1.0) (<= 0.0 ?VAR129) (<= ?VAR129 1.0) (<= 0.0 ?VAR130) (<= ?VAR130 1.0) (<= 0.0 ?VAR131) (<= ?VAR131 1.0) (<= 0.0 ?VAR132) (<= ?VAR132 1.0) (<= 0.0 ?VAR133) (<= ?VAR133 1.0) (<= 0.0 ?VAR134) (<= ?VAR134 1.0) (<= 0.0 ?VAR135) (<= ?VAR135 1.0) (<= 0.0 ?VAR136) (<= ?VAR136 1.0) (<= 0.0 ?VAR138) (<= ?VAR138 1.0) (<= 0.0 ?VAR137) (<= ?VAR137 1.0) (<= 0.0 ?VAR142) (<= ?VAR142 1.0) (<= 0.0 ?VAR141) (<= ?VAR141 1.0) (<= 0.0 ?VAR139) (<= ?VAR139 1.0) (<= 0.0 ?VAR140) (<= ?VAR140 1.0) (<= 0.0 ?VAR143) (<= ?VAR143 1.0) (<= 0.0 ?VAR144) (<= ?VAR144 1.0) (<= 0.0 ?VAR145) (<= ?VAR145 1.0) (<= 0.0 ?VAR146) (<= ?VAR146 1.0) (<= 0.0 ?VAR147) (<= ?VAR147 1.0) (<= 0.0 ?VAR148) (<= ?VAR148 1.0)))))
)

(benchmark plural43
; [0 < 1, 0 < VAR122, 1 == VAR122 + VAR123, 1 == 0 + VAR124, VAR125 == VAR122 + VAR123, VAR122 + VAR123 <= 1, VAR126 == 0 + VAR124, 0 + VAR124 <= 1, 0 < VAR127, VAR125 == VAR127 + VAR128, VAR126 == 1 + VAR129, VAR130 == VAR127 + VAR128, VAR127 + VAR128 <= 1, VAR131 == 1 + VAR129, 1 + VAR129 <= 1, 0 < VAR132, VAR130 == VAR132 + VAR133, VAR131 == 0 + VAR134, VAR135 == VAR132 + VAR133, VAR132 + VAR133 <= 1, VAR136 == 0 + VAR134, 0 + VAR134 <= 1, 0 < VAR138, VAR138 <= VAR137, 1 == VAR136, VAR142 <= VAR141, 1 == VAR139 + VAR141, 1 == VAR140 + VAR142, VAR140 <= VAR139, VAR139 == VAR137 + VAR143, VAR140 == VAR138 + VAR144, 1 == 1 + VAR145, VAR144 <= VAR143, VAR146 == VAR137 + VAR143, VAR137 + VAR143 <= 1, VAR147 == VAR138 + VAR144, VAR138 + VAR144 <= 1, VAR148 == 1 + VAR145, 1 + VAR145 <= 1, VAR147 <= VAR146, 0 < VAR149, 1 == VAR142 + VAR147, 1 == VAR149 + VAR150, 1 == 1 + VAR151, VAR152 == VAR149 + VAR150, VAR149 + VAR150 <= 1, VAR153 == 1 + VAR151, 1 + VAR151 <= 1]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR122 Real) (?VAR123 Real) (?VAR124 Real) (?VAR125 Real) (?VAR126 Real) (?VAR127 Real) (?VAR128 Real) (?VAR129 Real) (?VAR130 Real) (?VAR131 Real) (?VAR132 Real) (?VAR133 Real) (?VAR134 Real) (?VAR135 Real) (?VAR136 Real) (?VAR138 Real) (?VAR137 Real) (?VAR142 Real) (?VAR141 Real) (?VAR139 Real) (?VAR140 Real) (?VAR143 Real) (?VAR144 Real) (?VAR145 Real) (?VAR146 Real) (?VAR147 Real) (?VAR148 Real) (?VAR149 Real) (?VAR150 Real) (?VAR151 Real) (?VAR152 Real) (?VAR153 Real) (and (< 0.0 ?VAR122) (= 1.0 (+ ?VAR122 ?VAR123)) (= 1.0 (+ 0.0 ?VAR124)) (= ?VAR125 (+ ?VAR122 ?VAR123)) (<= (+ ?VAR122 ?VAR123) 1.0) (= ?VAR126 (+ 0.0 ?VAR124)) (<= (+ 0.0 ?VAR124) 1.0) (< 0.0 ?VAR127) (= ?VAR125 (+ ?VAR127 ?VAR128)) (= ?VAR126 (+ 1.0 ?VAR129)) (= ?VAR130 (+ ?VAR127 ?VAR128)) (<= (+ ?VAR127 ?VAR128) 1.0) (= ?VAR131 (+ 1.0 ?VAR129)) (<= (+ 1.0 ?VAR129) 1.0) (< 0.0 ?VAR132) (= ?VAR130 (+ ?VAR132 ?VAR133)) (= ?VAR131 (+ 0.0 ?VAR134)) (= ?VAR135 (+ ?VAR132 ?VAR133)) (<= (+ ?VAR132 ?VAR133) 1.0) (= ?VAR136 (+ 0.0 ?VAR134)) (<= (+ 0.0 ?VAR134) 1.0) (< 0.0 ?VAR138) (<= ?VAR138 ?VAR137) (= 1.0 ?VAR136) (<= ?VAR142 ?VAR141) (= 1.0 (+ ?VAR139 ?VAR141)) (= 1.0 (+ ?VAR140 ?VAR142)) (<= ?VAR140 ?VAR139) (= ?VAR139 (+ ?VAR137 ?VAR143)) (= ?VAR140 (+ ?VAR138 ?VAR144)) (= 1.0 (+ 1.0 ?VAR145)) (<= ?VAR144 ?VAR143) (= ?VAR146 (+ ?VAR137 ?VAR143)) (<= (+ ?VAR137 ?VAR143) 1.0) (= ?VAR147 (+ ?VAR138 ?VAR144)) (<= (+ ?VAR138 ?VAR144) 1.0) (= ?VAR148 (+ 1.0 ?VAR145)) (<= (+ 1.0 ?VAR145) 1.0) (<= ?VAR147 ?VAR146) (< 0.0 ?VAR149) (= 1.0 (+ ?VAR142 ?VAR147)) (= 1.0 (+ ?VAR149 ?VAR150)) (= 1.0 (+ 1.0 ?VAR151)) (= ?VAR152 (+ ?VAR149 ?VAR150)) (<= (+ ?VAR149 ?VAR150) 1.0) (= ?VAR153 (+ 1.0 ?VAR151)) (<= (+ 1.0 ?VAR151) 1.0) (<= 0.0 ?VAR122) (<= ?VAR122 1.0) (<= 0.0 ?VAR123) (<= ?VAR123 1.0) (<= 0.0 ?VAR124) (<= ?VAR124 1.0) (<= 0.0 ?VAR125) (<= ?VAR125 1.0) (<= 0.0 ?VAR126) (<= ?VAR126 1.0) (<= 0.0 ?VAR127) (<= ?VAR127 1.0) (<= 0.0 ?VAR128) (<= ?VAR128 1.0) (<= 0.0 ?VAR129) (<= ?VAR129 1.0) (<= 0.0 ?VAR130) (<= ?VAR130 1.0) (<= 0.0 ?VAR131) (<= ?VAR131 1.0) (<= 0.0 ?VAR132) (<= ?VAR132 1.0) (<= 0.0 ?VAR133) (<= ?VAR133 1.0) (<= 0.0 ?VAR134) (<= ?VAR134 1.0) (<= 0.0 ?VAR135) (<= ?VAR135 1.0) (<= 0.0 ?VAR136) (<= ?VAR136 1.0) (<= 0.0 ?VAR138) (<= ?VAR138 1.0) (<= 0.0 ?VAR137) (<= ?VAR137 1.0) (<= 0.0 ?VAR142) (<= ?VAR142 1.0) (<= 0.0 ?VAR141) (<= ?VAR141 1.0) (<= 0.0 ?VAR139) (<= ?VAR139 1.0) (<= 0.0 ?VAR140) (<= ?VAR140 1.0) (<= 0.0 ?VAR143) (<= ?VAR143 1.0) (<= 0.0 ?VAR144) (<= ?VAR144 1.0) (<= 0.0 ?VAR145) (<= ?VAR145 1.0) (<= 0.0 ?VAR146) (<= ?VAR146 1.0) (<= 0.0 ?VAR147) (<= ?VAR147 1.0) (<= 0.0 ?VAR148) (<= ?VAR148 1.0) (<= 0.0 ?VAR149) (<= ?VAR149 1.0) (<= 0.0 ?VAR150) (<= ?VAR150 1.0) (<= 0.0 ?VAR151) (<= ?VAR151 1.0) (<= 0.0 ?VAR152) (<= ?VAR152 1.0) (<= 0.0 ?VAR153) (<= ?VAR153 1.0)))))
)

(benchmark plural44
; [0 < 1, 0 < VAR122, 1 == VAR122 + VAR123, 1 == 0 + VAR124, VAR125 == VAR122 + VAR123, VAR122 + VAR123 <= 1, VAR126 == 0 + VAR124, 0 + VAR124 <= 1, 0 < VAR127, VAR125 == VAR127 + VAR128, VAR126 == 1 + VAR129, VAR130 == VAR127 + VAR128, VAR127 + VAR128 <= 1, VAR131 == 1 + VAR129, 1 + VAR129 <= 1, 0 < VAR132, VAR130 == VAR132 + VAR133, VAR131 == 0 + VAR134, VAR135 == VAR132 + VAR133, VAR132 + VAR133 <= 1, VAR136 == 0 + VAR134, 0 + VAR134 <= 1, VAR125 == 1 + VAR154, VAR126 == 1 + VAR155]
  :status unsat
  :assumption (< 0.0 1.0)
  :formula (not (implies (< 0.0 1.0) (exists (?VAR122 Real) (?VAR123 Real) (?VAR124 Real) (?VAR125 Real) (?VAR126 Real) (?VAR127 Real) (?VAR128 Real) (?VAR129 Real) (?VAR130 Real) (?VAR131 Real) (?VAR132 Real) (?VAR133 Real) (?VAR134 Real) (?VAR135 Real) (?VAR136 Real) (?VAR154 Real) (?VAR155 Real) (and (< 0.0 ?VAR122) (= 1.0 (+ ?VAR122 ?VAR123)) (= 1.0 (+ 0.0 ?VAR124)) (= ?VAR125 (+ ?VAR122 ?VAR123)) (<= (+ ?VAR122 ?VAR123) 1.0) (= ?VAR126 (+ 0.0 ?VAR124)) (<= (+ 0.0 ?VAR124) 1.0) (< 0.0 ?VAR127) (= ?VAR125 (+ ?VAR127 ?VAR128)) (= ?VAR126 (+ 1.0 ?VAR129)) (= ?VAR130 (+ ?VAR127 ?VAR128)) (<= (+ ?VAR127 ?VAR128) 1.0) (= ?VAR131 (+ 1.0 ?VAR129)) (<= (+ 1.0 ?VAR129) 1.0) (< 0.0 ?VAR132) (= ?VAR130 (+ ?VAR132 ?VAR133)) (= ?VAR131 (+ 0.0 ?VAR134)) (= ?VAR135 (+ ?VAR132 ?VAR133)) (<= (+ ?VAR132 ?VAR133) 1.0) (= ?VAR136 (+ 0.0 ?VAR134)) (<= (+ 0.0 ?VAR134) 1.0) (= ?VAR125 (+ 1.0 ?VAR154)) (= ?VAR126 (+ 1.0 ?VAR155)) (<= 0.0 ?VAR122) (<= ?VAR122 1.0) (<= 0.0 ?VAR123) (<= ?VAR123 1.0) (<= 0.0 ?VAR124) (<= ?VAR124 1.0) (<= 0.0 ?VAR125) (<= ?VAR125 1.0) (<= 0.0 ?VAR126) (<= ?VAR126 1.0) (<= 0.0 ?VAR127) (<= ?VAR127 1.0) (<= 0.0 ?VAR128) (<= ?VAR128 1.0) (<= 0.0 ?VAR129) (<= ?VAR129 1.0) (<= 0.0 ?VAR130) (<= ?VAR130 1.0) (<= 0.0 ?VAR131) (<= ?VAR131 1.0) (<= 0.0 ?VAR132) (<= ?VAR132 1.0) (<= 0.0 ?VAR133) (<= ?VAR133 1.0) (<= 0.0 ?VAR134) (<= ?VAR134 1.0) (<= 0.0 ?VAR135) (<= ?VAR135 1.0) (<= 0.0 ?VAR136) (<= ?VAR136 1.0) (<= 0.0 ?VAR154) (<= ?VAR154 1.0) (<= 0.0 ?VAR155) (<= ?VAR155 1.0)))))
)
