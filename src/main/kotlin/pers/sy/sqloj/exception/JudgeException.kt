package pers.sy.sqloj.exception

class JudgeServerNotFoundException : Exception()

class NoSuchJudgeServerException(s: String) : Exception(s)

class JudgeServerPasswordException : Exception()
