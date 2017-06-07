package com.forumsite.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService; 
import javax.enterprise.concurrent.ManagedExecutorService; 
import javax.inject.Inject;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadManagement;

/**
 * 
 * The HitResolver class provides methods to check and store
 * a product's views(hits) outside of accessing the database
 * or modifying the cache on each product select.
 * <p>It also updates the database on a set interval
 *
 */
@Singleton
@Startup
public class HitResolver {

    private Map<Long,HitNode> threadHits = new ConcurrentHashMap<>();
    
    @Inject
    private ForumThreadManagement management;
    
    @Resource
    private ManagedScheduledExecutorService managedScheduledExecutorService;
    
    @Resource
    private ManagedExecutorService managedExecutorService;
    
    @PostConstruct
    public void init(){
        managedScheduledExecutorService.scheduleWithFixedDelay(new UpdateDatabaseRunnable(), 5, 40, TimeUnit.MINUTES);
    }
    

    
    public void resolve(ForumThread t){
        cacheResolve(t);
        updateCache(t);
    }
    
    public void resolve(List<ForumThread> threads){
        threads.stream()
               .forEach(this::cacheResolve);
    }
    
    private void cacheResolve(ForumThread t){
        if(threadHits.containsKey(t.getId())){
            t.setHits(threadHits.get(t.getId()).getHits() + 1);
        }else{
            t.setHits(t.getHits() + 1);
        }        
    }
    
    private void updateCache(ForumThread t){
        managedExecutorService.execute(new UpdateMapRunnable(t));
    }
    
    private class UpdateMapRunnable implements Runnable {
        
        private ForumThread t;
        
        protected UpdateMapRunnable(ForumThread t){
            this.t = t;
        }

        @Override
        public void run() {
            synchronized (threadHits) {
                threadHits.put(t.getId(),new HitNode(t.getId(),t.getHits()));
            }
        }
    }
    
    private class UpdateDatabaseRunnable implements Runnable {
        
        @Override
        public void run(){
            Map<Long,Long> toBeUpdated = threadHits.values()
                                                   .stream()
                                                   .filter(n -> n.isUpdated())
                                                   .map(n -> { n.setUpdated(false);
                                                               return n;})
                                                   .collect(Collectors.toMap(HitNode::getThreadId, HitNode::getHits));
            if(toBeUpdated.size() < 1){ return; }
            management.updateHits(toBeUpdated);
        }
    }
    
    private class HitNode {
        
        private long threadId;
        
        private long hits;
        
        private boolean updated;
        
        protected HitNode(long threadId,long hits){
            this.threadId = threadId;
            this.hits = hits;
            this.updated = true;
        }

        public long getThreadId() {
            return threadId;
        }

        public long getHits() {
            return hits;
        }

        public boolean isUpdated() {
            return updated;
        }

        public void setUpdated(boolean updated) {
            this.updated = updated;
        }

    }
    
}


