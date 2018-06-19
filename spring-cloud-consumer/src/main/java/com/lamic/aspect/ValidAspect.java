/*package com.lamic.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.lamic.domain.BaseResult;
import com.lamic.domain.BindingError;

@Aspect
@Repository
public class ValidAspect {
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();  
        Method method = signature.getMethod();  
        if(!BaseResult.class.equals(method.getReturnType())){  
            pjp.proceed();  
        }
        Object[] args = pjp.getArgs();  
        Annotation[][] annotations = method.getParameterAnnotations();  
        for(int i = 0; i < annotations.length; i++){  
            if(!hasValidAnnotation(annotations[i])){  
                continue;  
            }  
            if(!(i < annotations.length-1 && args[i+1] instanceof BindingResult)){  
                //验证对象后面没有跟bindingResult,事实上如果没有应该到不了这一步  
                continue;  
            }  
            BindingResult result = (BindingResult) args[i+1];  
            if(result.hasErrors()){  
            	BaseResult baseResult = new BaseResult();  
            	baseResult.setSuccess(false);  
            	baseResult.setObj(processErrors(result));  
                return baseResult;  
            }  
        }  
        return pjp.proceed();
	}

	private boolean hasValidAnnotation(Annotation[] annotations) {
		if (annotations == null) {
			return false;
		}
		for (Annotation annotation : annotations) {
			if (annotation instanceof Valid) {
				return true;
			}
		}
		return false;
	}
	
	private List<BindingError> processErrors(BindingResult result){  
        if(result != null && result.hasErrors()){  
            List<BindingError> list = new ArrayList<BindingError>();  
            for(ObjectError objectError : result.getAllErrors()){  
            	FieldError error = (FieldError) objectError;
                BindingError be = new BindingError();  
                be.setMessage(error.getDefaultMessage());
                be.setName(error.getField());  
                list.add(be);  
            }  
            return list;  
        }  
        return null;  
    }
}
*/