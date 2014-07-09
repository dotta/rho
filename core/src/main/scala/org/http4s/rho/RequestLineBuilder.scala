package org.http4s
package rho

import bits.PathAST.PathRule
import org.http4s.rho.bits.QueryAST.{QueryAnd, TypedQuery, QueryRule}
import bits.HeaderAST.{EmptyHeaderRule, HeaderRule}

import shapeless.HList
import shapeless.ops.hlist.Prepend


case class RequestLineBuilder[T <: HList](path: PathRule, query: QueryRule) extends TypedBuilder[T] {
  override def validators: HeaderRule = EmptyHeaderRule

  def &[T1 <: HList](q: TypedQuery[T1])(implicit prep: Prepend[T1, T]): RequestLineBuilder[prep.Out] =
    RequestLineBuilder(path, QueryAnd(query, q.rule))
}