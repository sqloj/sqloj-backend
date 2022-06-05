package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pers.sy.sqloj.entity.ArticleDO
import pers.sy.sqloj.exception.ArticleNotFoundException
import pers.sy.sqloj.service.ArticleService
import pers.sy.sqloj.util.VResponse


@RestController
@RequestMapping("/api/v1/Article")
@Tag(name = "SQLOJ 文章管理", description = "ArticleController")
class ArticleController @Autowired constructor(
    val articleService: ArticleService
) {
    @GetMapping("/list")
    @Operation(summary = "文章列表")
    @ApiResponse(description = "文章列表")
    fun list(): VResponse<Any?> {
        return VResponse.ok(articleService.list())
    }


    @GetMapping("/info/{id}")
    @Operation(summary = "文章信息")
    @ApiResponse(description = "文章信息")
    fun info(
        @PathVariable("id") @Parameter(description = "文章 ID") id: Int
    ): VResponse<Any?> {
        try {
            val article = articleService.getByID(id)
            return VResponse.ok(article)
        } catch (e: ArticleNotFoundException) {
            return VResponse.err(1, "文章不存在")
        }
    }

    @PostMapping("/insert")
    @Operation(summary = "增加文章")
    fun insert(
        @RequestBody @Parameter(description = "文章信息") entity: ArticleDO
    ): VResponse<Any?> {
        try {
//            val article = ArticleDO(entity.title, entity.content);
//            println(article.toString());
            articleService.insert(entity)
            return VResponse.ok(entity)
        } catch (e: ArticleNotFoundException) {
            return VResponse.err(1, "文章不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "加入失败")
        }
    }

    @PostMapping("/update")
    @Operation(summary = "更新文章")
    fun update(
        @RequestBody @Parameter(description = "文章信息") entity: ArticleDO
    ): VResponse<Any?> {
        try {
            articleService.update(entity)
            return VResponse.ok(entity)
        } catch (e: ArticleNotFoundException) {
            return VResponse.err(1, "文章不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "更新文章失败")
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "删除文章")
    fun update(
        @RequestParam @Parameter(description = "文章信息") id: Int
    ): VResponse<Any?> {
        try {
            articleService.delete(id)
            return VResponse.ok()
        } catch (e: ArticleNotFoundException) {
            return VResponse.err(1, "文章不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "更新文章失败")
        }
    }
}