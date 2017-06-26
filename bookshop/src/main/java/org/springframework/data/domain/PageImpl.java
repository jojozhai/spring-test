/**
 * 
 */
package org.springframework.data.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

/**
 * @author zhailiang
 * @since 2016年4月13日
 */
public class PageImpl<T> extends Chunk<T> implements Page<T> {

	private static final long serialVersionUID = 867755909294344406L;

	private final long total;
	private final Pageable pageable;
	
	public PageImpl() {
		this(new ArrayList<T>());
	}

	/**
	 * Constructor of {@code PageImpl}.
	 * 
	 * @param content the content of this page, must not be {@literal null}.
	 * @param pageable the paging information, can be {@literal null}.
	 * @param total the total amount of items available. The total might be adapted considering the length of the content
	 *          given, if it is going to be the content of the last page. This is in place to mitigate inconsistencies
	 */
	public PageImpl(List<T> content, Pageable pageable, long total) {

		super(content, pageable);

		this.pageable = pageable;
		this.total = !content.isEmpty() && pageable != null && pageable.getOffset() + pageable.getPageSize() > total
				? pageable.getOffset() + content.size() : total;
	}

	/**
	 * Creates a new {@link PageImpl} with the given content. This will result in the created {@link Page} being identical
	 * to the entire {@link List}.
	 * 
	 * @param content must not be {@literal null}.
	 */
	public PageImpl(List<T> content) {
		this(content, null, null == content ? 0 : content.size());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Page#getTotalPages()
	 */
	@Override
	public int getTotalPages() {
		return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Page#getTotalElements()
	 */
	@Override
	public long getTotalElements() {
		return total;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return getNumber() + 1 < getTotalPages();
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#isLast()
	 */
	@Override
	public boolean isLast() {
		return !hasNext();
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#transform(org.springframework.core.convert.converter.Converter)
	 */
	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		return new PageImpl<S>(getConvertedContent(converter), pageable, total);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String contentType = "UNKNOWN";
		List<T> content = getContent();

		if (content.size() > 0) {
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageImpl<?>)) {
			return false;
		}

		PageImpl<?> that = (PageImpl<?>) obj;

		return this.total == that.total && super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int result = 17;

		result += 31 * (int) (total ^ total >>> 32);
		result += 31 * super.hashCode();

		return result;
	}
	

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getNumber()
     */
    public int getNumber() {
        return pageable == null ? 0 : pageable.getPageNumber();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getSize()
     */
    public int getSize() {
        return pageable == null ? 0 : pageable.getPageSize();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getNumberOfElements()
     */
    public int getNumberOfElements() {
        return getContent().size();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#hasPrevious()
     */
    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#isFirst()
     */
    public boolean isFirst() {
        return !hasPrevious();
    }


    /* 
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#nextPageable()
     */
    public Pageable nextPageable() {
        return hasNext() ? pageable.next() : null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#previousPageable()
     */
    public Pageable previousPageable() {

        if (hasPrevious()) {
            return pageable.previousOrFirst();
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#hasContent()
     */
    public boolean hasContent() {
        return !getContent().isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getSort()
     */
    public Sort getSort() {
        return pageable == null ? null : pageable.getSort();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<T> iterator() {
        return getContent().iterator();
    }

    /**
     * Applies the given {@link Converter} to the content of the {@link Chunk}.
     * 
     * @param converter must not be {@literal null}.
     * @return
     */
    protected <S> List<S> getConvertedContent(Converter<? super T, ? extends S> converter) {

        Assert.notNull(converter, "Converter must not be null!");

        List<S> result = new ArrayList<S>(getContent().size());

        for (T element : this) {
            result.add(converter.convert(element));
        }

        return result;
    }

	
}
