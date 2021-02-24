package com.hs.vueblog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.beans.Transient;
import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author hs
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "MUser对象", description = "")
@TableName("m_user")
public class MUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    private String avatar;

    private String email;

    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    @JsonIgnore
    private Boolean status;

    @JsonIgnore
    private Date created;

    @JsonIgnore
    private Date lastLogin;

    @JsonIgnore
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @JsonIgnore
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    private String token;
}
