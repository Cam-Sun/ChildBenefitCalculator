package ChildBenefit

object ChildBenefit extends App {
  val EldestChildRate = 26.05 //per week
  val FurtherChildRate = 17.25 //per week
  val reducedRateOneChild = 2.88 //per week
  val reducedRateTwoOrMore = 5.77 //per week per child
  val additionalDisabledRateBenefit = 200.0 //per year

  // function to verify individual child is eligible or not
  def isChildEligible(childInFamily: ChildInFamily): Boolean = {
    if (childInFamily.age <= 16) true
    else if (childInFamily.age < 20 && childInFamily.inEducation) true
    else false
  }

  //  /** disabled child rate * */
  // extra benefit for all disabled children for family whose income <= 100000
    def additionalDisabledBenefitRate(children: List[ChildInFamily], income: Int): BigDecimal = {
    val countChildrenWithDisability = children.filter(_.isDisabled == true)
    if (countChildrenWithDisability.nonEmpty && income <= 100000)
      countChildrenWithDisability.length * 3.85
    else
      BigDecimal(0)
  }
// weekly benefit for different income bandings exclude additional benefit for disabled children
  def calculateWeeklyAmount(children: List[ChildInFamily], income: Int): BigDecimal = {
    val eligible = children.filter(isChildEligible)
    eligible match {
      case Nil => BigDecimal(0) // Case when there are no eligible children
      case _ if income <= 50000 =>
        BigDecimal(EldestChildRate) + (eligible.length - 1) * BigDecimal(FurtherChildRate)
      case _ if income >= 50001 && income <= 100000 && eligible.length == 1 =>
        BigDecimal(reducedRateOneChild)
      case _ if income >= 50001 && income <= 100000 && eligible.length >= 2 =>
        BigDecimal(reducedRateTwoOrMore) * eligible.length
      case _ => BigDecimal(0) // Default case
    }
  }

  def finalTotalValue(children: List[ChildInFamily], income: Int) = {
    calculateWeeklyAmount(children, income) + additionalDisabledBenefitRate(children, income)
  }

  /** disabled child rate * */
  def additionalDisabledBenefitRate(children: List[ChildInFamily]): Double = {
    children.count(_.isDisabled) * additionalDisabledRateBenefit
  }

  //EXT

  /* ??? find the eldest, then check their eligibility, further calculate the potential benefit 
   or find the eldest from the eligible children??
   */
  def calculateYearlyAmountEldest(children: List[ChildInFamily], income: Int): BigDecimal = {
    val eligible = children.filter(isChildEligible)
//    val hasEldestAdditionalBenefit =
      eligible match {
        case Nil => BigDecimal(0) // Case when there are no eligible children
        // income <=50000 can claim full rate regardless of number of children
        case _ if income <= 50000 =>
          BigDecimal(EldestChildRate) * 52
        case _ if income >= 50001 && income <= 100000 && eligible.length == 1 =>
          BigDecimal(reducedRateOneChild) * 52
        case _ if income >= 50001 && income <= 100000 && eligible.length >= 2 =>
          BigDecimal(reducedRateTwoOrMore) * 52
        case _ => BigDecimal(0) // Default case
      }
  }

    
  def calculateYearlyAmountFurtherChild(): Double = ???
}
