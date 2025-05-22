package ChildBenefit

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ChildBenefitSpec extends AnyWordSpec with Matchers {  
  "isChildEligible" should {    
    "return true" when {      
      "child is younger than 16"in {   
        val child = ChildInFamily(age=15, inEducation = true, isDisabled = false)        
        val result = ChildBenefit.isChildEligible(child)        
        val expectedResult = true        
        result shouldBe expectedResult      
      }

      "isChildEligible" should {
        "return true" when {
          "child is younger than 20 and is in approved education" in {
            val child = ChildInFamily(age = 19, inEducation = true, isDisabled = false)
            val result = ChildBenefit.isChildEligible(child)
            val expectResult = true
            result shouldBe expectResult
          }
        }
      }
      "isChildEligible" should {
        "return false" when{
          "child is 20 years old and is in approved education" in {
            val child = ChildInFamily(age = 20, inEducation = true, isDisabled = false)
            val result = ChildBenefit.isChildEligible(child)
            val expectResult = false
            result shouldBe expectResult
          }
        }
      }

      "isChildEligible" should {
        "return false" when {
          "child is 21 years old and is in approved education" in {
            val child = ChildInFamily(age = 21, inEducation = true, isDisabled = false)
            val result = ChildBenefit.isChildEligible(child)
            val expectResult = false
            result shouldBe expectResult
          }
        }
      }

      "calculateYearlyAmountEldest" should {
    "calculate annual benefit for the eldest child " when {
      "the family has 1 eligible child and family income is 50000 " in {
        val children = List(
        ChildInFamily(age = 5, inEducation = true, isDisabled = false),
          ChildInFamily(age = 20, inEducation = true, isDisabled = false))
        val income = 50000
        val result = ChildBenefit.calculateYearlyAmountEldest(children, income)
        val expectResult = BigDecimal(26.05) * 52
        result shouldBe expectResult
      }
    }
  }
//      "calculateYearlyAmountEldest" should {
//        "calculate annual benefit for the eldest child " when {
//          "the family has 1 eligible child and family income is 50000, plus the child is disabled " in {
//            val children = List(
//              ChildInFamily(age = 5, inEducation = true, isDisabled = false),
//              ChildInFamily(age = 20, inEducation = true, isDisabled = false))
//            val income = 50000
//            val result = ChildBenefit.calculateYearlyAmountEldest(children, income)
//            val expectResult = BigDecimal(26.05 + 3.85) * 52
//            result shouldBe expectResult
//          }
//        }
//      }
      
      "calculateYearlyAmountEldest" should {
        "calculate annual benefit for the eldest child " when {
          "the family has 2 eligible children and the income is 50001 " in {
            val children = List(ChildInFamily(age = 3, inEducation = true, isDisabled = false),
              ChildInFamily(age = 5, inEducation = true, isDisabled = false),
              ChildInFamily(age = 20, inEducation = true, isDisabled = false))
            val income = 50001
            val result = ChildBenefit.calculateYearlyAmountEldest(children, income)
            val expectResult = BigDecimal(5.77) * 52
            result shouldBe expectResult
          }
        }
      }

      "calculateYearlyAmountEldest" should {
        "calculate annual benefit for the eldest child " when {
          "the family has 1 eligible child and family income is 50001 " in {
            val children = List(
              ChildInFamily(age = 5, inEducation = true, isDisabled = false),
              ChildInFamily(age = 20, inEducation = true, isDisabled = false))
            val income = 50001
            val result = ChildBenefit.calculateYearlyAmountEldest(children, income)
            val expectResult = BigDecimal(2.88) * 52
            result shouldBe expectResult
          }
        }
      }

      "calculateYearlyAmountEldest" should {
        "calculate annual benefit for the eldest child " when {
          "the family has 1 eligible child and family income is 100,001 " in {
            val children = List(
              ChildInFamily(age = 5, inEducation = true, isDisabled = false),
              ChildInFamily(age = 20, inEducation = true, isDisabled = false))
            val income = 100001
            val result = ChildBenefit.calculateYearlyAmountEldest(children, income)
            val expectResult = BigDecimal(0) * 52
            result shouldBe expectResult
          }
        }
      }
      

    }  
  }
}
